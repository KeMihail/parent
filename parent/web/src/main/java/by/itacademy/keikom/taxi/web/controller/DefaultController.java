package by.itacademy.keikom.taxi.web.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.keikom.taxi.services.ICarOrderServices;

@Controller
public class DefaultController {

	@Autowired
	private ICarOrderServices orderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {

		final HashMap<String, Object> model = new HashMap<>();
		model.put("latestCarOrderId", orderService.getLatestOrderId());
		return new ModelAndView("index", model);
	}

}
