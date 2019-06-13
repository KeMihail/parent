package by.itacademy.keikom.taxi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.eclipse.core.internal.resources.MoveDeleteHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.dbmodel.Driver_;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.Model_;
import by.itacademy.keikom.taxi.dao.filter.CarFilter;
import by.itacademy.keikom.taxi.dao.filter.DriverFilter;
import by.itacademy.keikom.taxi.dao.filter.ModelFilter;
import by.itacademy.keikom.taxi.services.ICarServices;
import by.itacademy.keikom.taxi.services.IDriverServices;
import by.itacademy.keikom.taxi.web.converter.DriverFromDTOConverter;
import by.itacademy.keikom.taxi.web.converter.DriverToDTOConverter;
import by.itacademy.keikom.taxi.web.dto.DriverDTO;
import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;

@Component
@RequestMapping(value = "/driver")
public class DriverController {

	private static final String LOCAL_LIST_MODEL_NAME = "modelListModel";

	@Autowired
	private IDriverServices driverService;
	@Autowired
	private ICarServices carServise;
	@Autowired
	private DriverToDTOConverter toDTOConverter;
	@Autowired
	private DriverFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<DriverDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<DriverDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		DriverFilter driverFilter = buildFilter(listModel);

		final List<Driver> currentPageList = driverService.getAll(driverFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(driverService.getCount(driverFilter));

		final ModelAndView mv = new ModelAndView("driver.list");
		return mv;
	}

	private DriverFilter buildFilter(ListModel<DriverDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		DriverFilter driverFilter = new DriverFilter();
		driverFilter.setLimit(listModel.getItemsPerPage());
		driverFilter.setOffset(offset);
		driverFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":
			sortAttribute = Driver_.id;
			break;
		case "address":
			sortAttribute = Driver_.address;
			break;
		case "birthday":
			sortAttribute = Driver_.birthday;
			break;
		case "email":
			sortAttribute = Driver_.email;
			break;
		case "lastName":
			sortAttribute = Driver_.lastName;
			break;
		case "name":
			sortAttribute = Driver_.name;
			break;
		case "phoneNumber":
			sortAttribute = Driver_.phoneNumber;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		driverFilter.setSortProperty(sortAttribute);
		return driverFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("driverForm", new DriverDTO());
		loadComboboxesModels(map);
		return new ModelAndView("driver.edit", map);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("driverForm") final DriverDTO dto, final BindingResult result) {

		if (result.hasErrors() || driverService.checkEmail(dto.getEmail())) {
			return "driver.edit";
		} else {
			driverService.save(fromDTOConverter.apply(dto));
			return "redirect:/driver";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {

		driverService.remove(id);

		return "redirect:/driver";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("driverForm", toDTOConverter.apply(driverService.getFullInfo(id)));
		loadComboboxesModels(map);
		return new ModelAndView("driver.edit", map);
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("driverForm", toDTOConverter.apply(driverService.getFullInfo(id)));
		map.put("readonly", true);
		loadComboboxesModels(map);
		return new ModelAndView("driver.edit", map);
	}

	private void loadComboboxesModels(final HashMap<String, Object> hashMap) {

		final List<Car> allCars = carServise.getAll(new CarFilter());
		final HashMap<Integer, String> modelMap = new HashMap<>();

		for (Car car : allCars) {
			modelMap.put(car.getId(), car.getModel().getName());
		}
		hashMap.put("modelChoices", modelMap);

		final HashMap<Integer, String> brandMap = new HashMap<>();

		for (Car car : allCars) {
			brandMap.put(car.getId(), car.getModel().getBrand().getName());
		}
		hashMap.put("brandChoices", brandMap);
	}
}
