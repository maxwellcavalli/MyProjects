package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.City;
import br.com.project.everest.repository.CityRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/city" })
public class CityController extends CrudController<City>{

	@Autowired
	CityRepository cityRepository; 
	
	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "City");
		model.setViewName("city");
		return model;
	}

	@Override
	public CrudRepository<City> getRepository() {
		return cityRepository;
	}

	@Override
	public void beforeSave(City t) {
	}

	@Override
	public void validationCreate(City t) throws Exception {
	}

}