package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.District;
import br.com.project.everest.repository.DistrictRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/district" })
public class DistrictController extends CrudController<District>{
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "District");
		model.setViewName("district");
		return model;
	}

	@Override
	public CrudRepository<District> getRepository() {
		return districtRepository;
	}

	@Override
	public void beforeSave(District t) {
	}

	@Override
	public void validationCreate(District t) throws Exception {
		if (t.getCity() == null || t.getCity().getId() == null || t.getCity().getId().longValue() == 0){
			throw new Exception("Invalid City");
		}
	}
}