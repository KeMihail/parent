package by.itacademy.keikom.taxi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder_;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.filter.DriverFilter;
import by.itacademy.keikom.taxi.dao.filter.CarOrderFilter;
import by.itacademy.keikom.taxi.dao.filter.RateFilter;
import by.itacademy.keikom.taxi.dao.filter.UserFilter;
import by.itacademy.keikom.taxi.services.IDriverServices;
import by.itacademy.keikom.taxi.services.ICarOrderServices;
import by.itacademy.keikom.taxi.services.IRateServices;
import by.itacademy.keikom.taxi.services.IUserServices;
import by.itacademy.keikom.taxi.web.converter.CarOrderFromConverter;
import by.itacademy.keikom.taxi.web.converter.CarOrderToDTOConverter;
import by.itacademy.keikom.taxi.web.dto.CarOrderDTO;
import by.itacademy.keikom.taxi.web.security.AuthHelper;
import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;

@Controller
@RequestMapping(value = "/carOrder")
public class CarOrderController {

	private static final String LOCAL_LIST_MODEL_NAME = "carOrderListModel";

	@Autowired
	private ICarOrderServices carOrderService;

	@Autowired
	private IRateServices rateService;

	@Autowired
	private IDriverServices driverService;

	@Autowired
	private IUserServices userService;

	@Autowired
	private CarOrderToDTOConverter toDTOConverter;

	@Autowired
	private CarOrderFromConverter fromDTOConverter;

	@RequestMapping(value = "/lastId", method = RequestMethod.GET)
	public ResponseEntity<Integer> checkNewOrders() {
		return new ResponseEntity<Integer>(carOrderService.getLatestOrderId(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<CarOrderDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<CarOrderDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		CarOrderFilter carOrderFilter = buildFilter(listModel);

		final List<CarOrder> currentPageList = carOrderService.getAll(carOrderFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(carOrderService.getCount(carOrderFilter));

		final ModelAndView mv = new ModelAndView("carOrder.list");
		return mv;
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView orderHistory(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<CarOrderDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<CarOrderDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		CarOrderFilter carOrderFilter = buildFilter(listModel);

		final HashMap<String, Object> map = new HashMap<>();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		String user;
		if (role.equals(new String("[ROLE_DRIVER]"))) {
			user = "driver";
		} else {
			user = "client";
		}

		final List<CarOrder> currentPageList = carOrderService.ordersToClient(AuthHelper.getLoggedUserId(), user);

		if (role.equals(new String("[ROLE_DRIVER]"))) {

			map.put("result", carOrderService.revenue(currentPageList));
		}

		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(carOrderService.getCount(carOrderFilter));

		final ModelAndView mv = new ModelAndView("carOrder.history", map);
		return mv;
	}

	private CarOrderFilter buildFilter(ListModel<CarOrderDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		CarOrderFilter carOrderFilter = new CarOrderFilter();
		carOrderFilter.setLimit(listModel.getItemsPerPage());
		carOrderFilter.setOffset(offset);
		carOrderFilter.setSortOrder(sortModel.isAscending());
		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":
			sortAttribute = CarOrder_.id;
			break;
		case "arrivalAddress":
			sortAttribute = CarOrder_.arrivalAddress;
			break;
		case "departureAddress":
			sortAttribute = CarOrder_.departureAddress;
			break;
		case "distanceOrder":
			sortAttribute = CarOrder_.distanceOrder;
			break;
		case "distancePaid":
			sortAttribute = CarOrder_.distancePaid;
			break;
		case "inactivityMinutes":
			sortAttribute = CarOrder_.inactivityMinutes;
			break;
		case "total":
			sortAttribute = CarOrder_.total;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		carOrderFilter.setSortProperty(sortAttribute);
		return carOrderFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("carOrderForm", new CarOrderDTO());
		loadComboboxesModels(map);
		return new ModelAndView("carOrder.edit", map);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("carOrderForm") final CarOrderDTO dto, final BindingResult result) {

		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (role.equals(new String("[ROLE_PASSENGER]"))) {
			dto.setClientId(AuthHelper.getLoggedUserId());
		}
		if (result.hasErrors()) {
			return "redirect:carOrder.edit";
		}
		carOrderService.save(fromDTOConverter.apply(dto));
		return "redirect:/";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {

		carOrderService.remove(id);
		return "redirect:/carOrder";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> map = new HashMap<>();

		CarOrderDTO dto = toDTOConverter.apply(carOrderService.get(id));

		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (role.equals(new String("[ROLE_DRIVER]"))) {
			dto.setDriverId(AuthHelper.getLoggedUserId());
		}

		map.put("carOrderForm", dto);
		loadComboboxesModels(map);
		return new ModelAndView("carOrder.edit", map);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("carOrderForm", toDTOConverter.apply(carOrderService.get(id)));
		map.put("readonly", true);
		loadComboboxesModels(map);
		return new ModelAndView("carOrder.edit", map);
	}

	private void loadComboboxesModels(final HashMap<String, Object> hashMap) {

		final List<Rate> allRates = rateService.getAll(new RateFilter());
		final HashMap<Integer, String> ratesMap = new HashMap<>();

		for (Rate rate : allRates) {
			ratesMap.put(rate.getId(), rate.getName());
		}
		hashMap.put("rateChoices", ratesMap);

		final List<Driver> allDriver = driverService.getAll(new DriverFilter());
		final HashMap<Integer, String> driversMap = new HashMap<>();

		for (Driver driver : allDriver) {
			driversMap.put(driver.getId(), driver.getName());
		}
		hashMap.put("driverChoices", driversMap);

		final List<User> allUsers = userService.getAll(new UserFilter());
		final HashMap<Integer, String> usersMap = new HashMap<>();

		for (User user : allUsers) {
			usersMap.put(user.getId(), user.getName());
		}
		hashMap.put("userChoices", usersMap);
	}
}
