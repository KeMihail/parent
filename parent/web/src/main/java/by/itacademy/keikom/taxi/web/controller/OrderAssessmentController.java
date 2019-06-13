package by.itacademy.keikom.taxi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment_;
import by.itacademy.keikom.taxi.dao.filter.OrderAssessmentFilter;
import by.itacademy.keikom.taxi.dao.filter.CarOrderFilter;
import by.itacademy.keikom.taxi.services.IOrderAssessmentServices;
import by.itacademy.keikom.taxi.services.ICarOrderServices;
import by.itacademy.keikom.taxi.web.converter.OrderAssessmentFromDTOConverter;
import by.itacademy.keikom.taxi.web.converter.OrderAssessmentToDTOConverter;
import by.itacademy.keikom.taxi.web.dto.OrderAssessmentDTO;
import by.itacademy.keikom.taxi.web.security.AuthHelper;
import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;

@Controller
@RequestMapping(value = "/orderAssessment")
public class OrderAssessmentController {

	private static final String LOCAL_LIST_MODEL_NAME = "orderlListModel";

	@Autowired
	private IOrderAssessmentServices service;

	@Autowired
	private ICarOrderServices carOrderService;

	@Autowired
	private OrderAssessmentToDTOConverter toDTOConverter;

	@Autowired
	private OrderAssessmentFromDTOConverter fromDTOConverter;

	private Integer orderId;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<OrderAssessmentDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<OrderAssessmentDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		OrderAssessmentFilter orderAssessmentFilter = buildFilter(listModel);

		final List<OrderAssessment> currentPageList = service.commentsDriver(AuthHelper.getLoggedUserId());
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(service.getCount(orderAssessmentFilter));

		final ModelAndView mv = new ModelAndView("orderAssessment.list");
		return mv;
	}

	@RequestMapping(value = "/commentsDriver", method = RequestMethod.GET)
	public ModelAndView commentsDriver(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<OrderAssessmentDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<OrderAssessmentDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		OrderAssessmentFilter orderAssessmentFilter = buildFilter(listModel);

		final List<OrderAssessment> currentPageList = service.getAll(orderAssessmentFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(service.getCount(orderAssessmentFilter));

		final HashMap<String, Object> map = new HashMap<>();
		map.put("averageMark", service.averageMark(currentPageList));

		final ModelAndView mv = new ModelAndView("orderAssessment.list", map);
		return mv;
	}

	@RequestMapping(value = "/{id}/orderComment", method = RequestMethod.GET)
	public ModelAndView orderComment(@PathVariable(name = "id", required = true) final Integer id) {

		final OrderAssessment dbModel = service.get(id);
		final HashMap<String, Object> hashMap = new HashMap<String, Object>();

		if (dbModel != null) {
			hashMap.put("orderAssessment", dbModel);
		} else {
			orderId = id;
		}

		return new ModelAndView("orderAssessment.order", hashMap);
	}

	private OrderAssessmentFilter buildFilter(ListModel<OrderAssessmentDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		OrderAssessmentFilter orderAssesmentFilter = new OrderAssessmentFilter();
		orderAssesmentFilter.setLimit(listModel.getItemsPerPage());
		orderAssesmentFilter.setOffset(offset);
		orderAssesmentFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":
			sortAttribute = OrderAssessment_.orderId;
			break;
		case "assessment":
			sortAttribute = OrderAssessment_.assessment;
			break;
		case "comment":
			sortAttribute = OrderAssessment_.comment;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		orderAssesmentFilter.setSortProperty(sortAttribute);
		return orderAssesmentFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		OrderAssessmentDTO dto = new OrderAssessmentDTO();
		dto.setOrderId(orderId);
		return new ModelAndView("orderAssessment.edit", "orderAssessmentForm", dto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("orderAssessmentForm") final OrderAssessmentDTO dto,
			final BindingResult result) {

		if (result.hasErrors()) {
			return "orderAssessment.edit";
		} else {
			service.save(fromDTOConverter.apply(dto));
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) Integer id) {
		service.remove(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderAssessmentForm", toDTOConverter.apply(service.get(id)));
		hashMap.put("readonly", true);

		return new ModelAndView("orderAssessment.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderAssessmentForm", toDTOConverter.apply(service.get(id)));
		loadComboboxesModels(hashMap);
		return new ModelAndView("orderAssessment.edit", hashMap);
	}

	private void loadComboboxesModels(HashMap<String, Object> hashMap) {

		final List<CarOrder> allCalls = carOrderService.getAll(new CarOrderFilter());
		final HashMap<Integer, String> ordersMap = new HashMap<Integer, String>();

		hashMap.put("ordersChoices", allCalls);

	}
}
