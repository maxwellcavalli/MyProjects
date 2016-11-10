package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.State;
import br.com.project.everest.repository.StateRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/state" })
public class StateController extends CrudController<State> {

	@Autowired
	StateRepository stateRepository; 
	
	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "State");
		model.setViewName("state");
		
		return model;
	}

	@Override
	public CrudRepository<State> getRepository() {
		return stateRepository;
	}

	@Override
	public void beforeSave(State t) {
		t.setAbreviation(t.getAbreviation().toUpperCase());		
	}

	@Override
	public void validationCreate(State t) throws Exception {
		if (stateRepository.findByAbreviation(t.getAbreviation()) != null) {
			String message = "A domain " + t.getAbreviation() + " already exist";
			System.out.println(message);

			throw new Exception(message);
		}
	}

}