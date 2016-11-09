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

import br.com.project.everest.domain.City;
import br.com.project.everest.helper.ResponseHelper;
import br.com.project.everest.repository.CityRepository;

@RestController
public class CityController {

	@Autowired
	CityRepository cityRepository; 

	@RequestMapping(value = { "/city" }, method = RequestMethod.GET)
	public ModelAndView mainMethod() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "City");
		model.setViewName("city");
		
		System.out.println("° ° ° ° welcomePage running. model = " + model);
		return model;
	}

	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> getDomain(@PathVariable("id") long id) {
		System.out.println("Fetching Domain with id " + id);
		
		City city = cityRepository.findById(id);
		if (city == null) {
			System.out.println("Domain with id " + id + " not found");
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}

	@RequestMapping(value = "/city/", method = RequestMethod.POST)
	public ResponseEntity<ResponseHelper<City>> createDomain(@RequestBody City city, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating domain " + city.getName());
		city = cityRepository.save(city);
		
		return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(city) , HttpStatus.CREATED);
	}

	@RequestMapping(value = "/city/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseHelper<City>> updateDomain(@PathVariable("id") long id, @RequestBody City city) {
		System.out.println("Updating Domain " + id);

		City currentDomain = cityRepository.findById(id);
		if (currentDomain == null) {
			String message = "Domain with id " + id + " not found";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(message), HttpStatus.NOT_FOUND);
		}

		currentDomain.setName(city.getName());
		currentDomain.setState(city.getState());
		currentDomain = cityRepository.save(currentDomain);
		
		return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(currentDomain), HttpStatus.OK);
	}

	@RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseHelper<City>> deleteDomain(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Domain with id " + id);

		City city = cityRepository.findById(id); 
		if (city == null) {
			String message = "Unable to delete. Domain with id " + id + " not found";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(message), HttpStatus.NOT_FOUND);
		}

		cityRepository.delete(id);
		return new ResponseEntity<ResponseHelper<City>>(HttpStatus.NO_CONTENT);
	}

}