package by.itacademy.keikom.taxi.web.controller;

import java.util.ArrayList;
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

import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.dbmodel.User_;
import by.itacademy.keikom.taxi.dao.enums.Role;
import by.itacademy.keikom.taxi.dao.filter.UserFilter;
import by.itacademy.keikom.taxi.services.IUserServices;
import by.itacademy.keikom.taxi.web.converter.UserFromDTOConverter;
import by.itacademy.keikom.taxi.web.converter.UserToDTOConverter;
import by.itacademy.keikom.taxi.web.dto.UserDTO;
import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final String LOCAL_LIST_MODEL_NAME = "userListModel";

	@Autowired
	private IUserServices userService;

	@Autowired
	private UserToDTOConverter toDTOConverter;;

	@Autowired
	private UserFromDTOConverter fromDTOConverter;;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewList(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false) final String sort,
			@RequestParam(name = "page", required = false) final Integer pageNumber) {

		ListModel<UserDTO> listModel;
		if (req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME) == null) {
			listModel = new ListModel<>();
			listModel.setSort(new SortModel("id"));
			req.getSession().setAttribute(LOCAL_LIST_MODEL_NAME, listModel);
		} else {
			listModel = (ListModel<UserDTO>) req.getSession().getAttribute(LOCAL_LIST_MODEL_NAME);
		}

		req.getSession().setAttribute(ListModel.SESSION_ATTR_NAME, listModel);

		listModel.setSort(sort);
		listModel.setPage(pageNumber);

		UserFilter userFilter = buildFilter(listModel);

		final List<User> currentPageList = userService.getAll(userFilter);
		listModel.setList(currentPageList.stream().map(toDTOConverter).collect(Collectors.toList()));
		listModel.setTotalCount(userService.getCount(userFilter));

		final ModelAndView mv = new ModelAndView("user.list");
		return mv;
	}

	private UserFilter buildFilter(ListModel<UserDTO> listModel) {

		SortModel sortModel = listModel.getSort();
		final int offset = listModel.getItemsPerPage() * (listModel.getPage() - 1);

		UserFilter userFilter = new UserFilter();
		userFilter.setLimit(listModel.getItemsPerPage());
		userFilter.setOffset(offset);
		userFilter.setSortOrder(sortModel.isAscending());

		SingularAttribute sortAttribute;
		switch (sortModel.getColumn()) {
		case "id":
			sortAttribute = User_.id;
			break;
		case "name":
			sortAttribute = User_.name;
			break;
		case "lastName":
			sortAttribute = User_.lastName;
			break;
		case "birthday":
			sortAttribute = User_.birthday;
			break;
		case "address":
			sortAttribute = User_.address;
			break;
		case "phoneNumber":
			sortAttribute = User_.phoneNumber;
			break;
		case "email":
			sortAttribute = User_.email;
			break;
		default:
			throw new IllegalArgumentException("unsupported sort property:" + sortModel.getColumn());
		}
		userFilter.setSortProperty(sortAttribute);
		return userFilter;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("userForm", new UserDTO());
		loadComboboxesModels(map);
		return new ModelAndView("user.edit", map);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("userForm") final UserDTO dto, final BindingResult result) {

		if (result.hasErrors() || userService.checkEmail(dto.getEmail())) {
			return "user.edit";
		} else {

			userService.save(fromDTOConverter.apply(dto));
			return "redirect:/user";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userService.remove(id);
		return "redirect:/user";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("userForm", toDTOConverter.apply(userService.get(id)));
		hashMap.put("readonly", true);

		return new ModelAndView("user.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("userForm", toDTOConverter.apply(userService.get(id)));
		loadComboboxesModels(map);

		return new ModelAndView("user.edit", map);
	}

	private void loadComboboxesModels(final HashMap<String, Object> hashMap) {
		List<Role> role = new ArrayList<>();
		role.add(Role.ADMIN);
		role.add(Role.PASSENGER);

		hashMap.put("roleChoices", role);
	}
}
