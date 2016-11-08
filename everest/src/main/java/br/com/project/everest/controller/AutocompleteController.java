package br.com.project.everest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AutocompleteController {

	@RequestMapping(value = { "/autocomplete" }, method = RequestMethod.GET)
	public ModelAndView autoCompletePage() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Example");
		model.addObject("message", "This is Hello World!");
		model.setViewName("autocomplete");
		System.out.println("° ° ° ° welcomePage running. model = " + model);
		return model;
	}
	
}