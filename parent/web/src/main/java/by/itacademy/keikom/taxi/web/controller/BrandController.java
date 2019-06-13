package by.itacademy.keikom.taxi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand_;
import by.itacademy.keikom.taxi.dao.filter.BrandFilter;
import by.itacademy.keikom.taxi.dao.filter.ModelFilter;
import by.itacademy.keikom.taxi.services.IBrandServices;
import by.itacademy.keikom.taxi.web.converter.BrandToDTOConverter;
import by.itacademy.keikom.taxi.web.dto.BrandDTO;
import by.itacademy.keikom.taxi.web.dto.ModelDTO;
import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;
import by.itacademy.keikom.taxi.web.converter.BrandFromDTOConverter;

@Controller
@RequestMapping(value = "/brand")
public class BrandController {

	private static final String LOCAL_LIST_MODEL_NAME = "brandListModel";

	@Autowired
	private IBrandServices brandService;
	@Autowired
	private BrandToDTOConverter toDTOConverter;
	@Autowired
	private BrandFromDTOConverter fromDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<BrandDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<BrandDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		BrandFilter brandFilter = buildFilter(listModel);

		final List<Brand> currentPageList = brandService.getAll(brandFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(brandService.getCount(brandFilter));

		final ModelAndView mv = new ModelAndView("brand.list");
		return mv;
	}

	private BrandFilter buildFilter(ListModel<BrandDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		BrandFilter brandFilter = new BrandFilter();
		brandFilter.setLimit(listModel.getItemsPerPage());
		brandFilter.setOffset(offset);
		brandFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":
			sortAttribute = Brand_.id;
			break;
		case "name":
			sortAttribute = Brand_.name;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		brandFilter.setSortProperty(sortAttribute);
		return brandFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("brand.edit", "brandForm", new BrandDTO());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("brandForm") final BrandDTO brandForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "brand.edit";
		} else {
			brandService.save(fromDTOConverter.apply(brandForm));
			return "redirect:/brand";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {

		brandService.remove(id);
		return "redirect:/brand";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("brandForm", toDTOConverter.apply(brandService.get(id)));
		hashMap.put("readonly", true);

		return new ModelAndView("brand.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {

		return new ModelAndView("brand.edit", "brandForm", toDTOConverter.apply(brandService.get(id)));
	}
}
