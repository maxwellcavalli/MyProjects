package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.project.everest.domain.State;
import br.com.project.everest.helper.ResponseHelper;
import br.com.project.everest.repository.StateRepository;

@RestController
public class StateController {

	@Autowired
	StateRepository stateRepository; 

	@RequestMapping(value = { "/state" }, method = RequestMethod.GET)
	public ModelAndView mainMethod() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "State");
		model.setViewName("state");
		
		System.out.println("° ° ° ° welcomePage running. model = " + model);
		return model;
	}

	@RequestMapping(value = "/state/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<State> getDomain(@PathVariable("id") long id) {
		System.out.println("Fetching Domain with id " + id);
		
		State state = stateRepository.findById(id);
		if (state == null) {
			System.out.println("Domain with id " + id + " not found");
			return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<State>(state, HttpStatus.OK);
	}

	@RequestMapping(value = "/state/", method = RequestMethod.POST)
	public ResponseEntity<ResponseHelper<State>> createDomain(@RequestBody State state, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating domain " + state.getName());

		if (stateRepository.findByAbreviation(state.getAbreviation()) != null) {
			String message = "A domain " + state.getAbreviation() + " already exist";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<State>>(new ResponseHelper<State>(message), HttpStatus.CONFLICT);
		}

		state.setAbreviation(state.getAbreviation().toUpperCase());
		state = stateRepository.save(state);
		
		return new ResponseEntity<ResponseHelper<State>>(new ResponseHelper<State>(state) , HttpStatus.CREATED);
	}

	@RequestMapping(value = "/state/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseHelper<State>> updateDomain(@PathVariable("id") long id, @RequestBody State state) {
		System.out.println("Updating Domain " + id);

		State currentDomain = stateRepository.findById(id);

		if (currentDomain == null) {
			String message = "Domain with id " + id + " not found";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<State>>(new ResponseHelper<State>(message), HttpStatus.NOT_FOUND);
		}

		currentDomain.setAbreviation(state.getAbreviation().toUpperCase());
		currentDomain.setName(state.getName());
		currentDomain = stateRepository.save(currentDomain);
		
		return new ResponseEntity<ResponseHelper<State>>(new ResponseHelper<State>(currentDomain), HttpStatus.OK);
	}

	@RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseHelper<State>> deleteDomain(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Domain with id " + id);

		State state = stateRepository.findById(id); 
		if (state == null) {
			String message = "Unable to delete. Domain with id " + id + " not found";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<State>>(new ResponseHelper<State>(message), HttpStatus.NOT_FOUND);
		}

		stateRepository.delete(id);
		return new ResponseEntity<ResponseHelper<State>>(HttpStatus.NO_CONTENT);
	}

//	@RequestMapping(value = "/state/", method = RequestMethod.DELETE)
//	public ResponseEntity<User> deleteAllUsers() {
//		System.out.println("Deleting All Users");
//
//		stateRepository.deleteAll();
//		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//	}

}