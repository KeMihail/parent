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

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption_;
import by.itacademy.keikom.taxi.dao.filter.CarOptionFilter;
import by.itacademy.keikom.taxi.services.ICarOptionServices;
import by.itacademy.keikom.taxi.web.converter.CarOptionFromDTOConverter;
import by.itacademy.keikom.taxi.web.converter.CarOptionToDTOConverter;
import by.itacademy.keikom.taxi.web.dto.CarOptionDTO;
import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;

@Controller
@RequestMapping(value = "/carOption")
public class CarOptionController {

	private static final String LOCAL_LIST_MODEL_NAME = "carOptionListModel";

	@Autowired
	private ICarOptionServices carOptionService;

	@Autowired
	private CarOptionFromDTOConverter fromDTOConverter;

	@Autowired
	private CarOptionToDTOConverter toDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<CarOptionDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<CarOptionDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		CarOptionFilter carOptionFilter = buildFilter(listModel);

		final List<CarOption> currentPageList = carOptionService.getAll(carOptionFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(carOptionService.getCount(carOptionFilter));

		final ModelAndView mv = new ModelAndView("carOption.list");
		return mv;
	}

	private CarOptionFilter buildFilter(ListModel<CarOptionDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		CarOptionFilter carOptionFilter = new CarOptionFilter();
		carOptionFilter.setLimit(listModel.getItemsPerPage());
		carOptionFilter.setOffset(offset);
		carOptionFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":
			sortAttribute = CarOption_.id;
			break;
		case "name":
			sortAttribute = CarOption_.name;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		carOptionFilter.setSortProperty(sortAttribute);
		return carOptionFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("carOption.edit", "carOptionForm", new CarOptionDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("carOptionForm") final CarOptionDTO dto, BindingResult result) {

		if (result.hasErrors()) {
			return "carOption.edit";
		} else {
			carOptionService.save(fromDTOConverter.apply(dto));
		}
		return "redirect:/carOption";
	}

	@RequestMapping(value = "/{id}/delete")
	public String delete(@PathVariable(name = "id", required = true) Integer id) {

		carOptionService.remove(id);
		return "redirect:/carOption";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) Integer id) {

		return new ModelAndView("carOption.edit", "carOptionForm", toDTOConverter.apply(carOptionService.get(id)));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("carOptionForm", toDTOConverter.apply(carOptionService.get(id)));
		hashMap.put("readonly", true);

		return new ModelAndView("carOption.edit", hashMap);
	}
}
