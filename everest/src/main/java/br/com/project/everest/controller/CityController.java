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
	
	

//	@RequestMapping(value = { "/city" }, method = RequestMethod.GET)
//	public ModelAndView mainMethod() {
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "City");
//		model.setViewName("city");
//		
//		System.out.println("° ° ° ° welcomePage running. model = " + model);
//		return model;
//	}
//
//	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<City> getDomain(@PathVariable("id") long id) {
//		System.out.println("Fetching Domain with id " + id);
//		
//		City domain = cityRepository.findById(id);
//		if (domain == null) {
//			System.out.println("Domain with id " + id + " not found");
//			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<City>(domain, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/city/", method = RequestMethod.POST)
//	public ResponseEntity<ResponseHelper<City>> createDomain(@RequestBody City domain, UriComponentsBuilder ucBuilder) {
//		System.out.println("Creating domain " + domain.getName());
//		domain = cityRepository.save(domain);
//		
//		return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(domain) , HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/city/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<ResponseHelper<City>> updateDomain(@PathVariable("id") long id, @RequestBody City domain) {
//		System.out.println("Updating Domain " + id);
//
//		City currentDomain = cityRepository.findById(id);
//		if (currentDomain == null) {
//			String message = "Domain with id " + id + " not found";
//			System.out.println(message);
//			
//			return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(message), HttpStatus.NOT_FOUND);
//		}
//
//		currentDomain.setName(domain.getName());
//		currentDomain.setState(domain.getState());
//		currentDomain = cityRepository.save(currentDomain);
//		
//		return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(currentDomain), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<ResponseHelper<City>> deleteDomain(@PathVariable("id") long id) {
//		System.out.println("Fetching & Deleting Domain with id " + id);
//
//		City domain = cityRepository.findById(id); 
//		if (domain == null) {
//			String message = "Unable to delete. Domain with id " + id + " not found";
//			System.out.println(message);
//			
//			return new ResponseEntity<ResponseHelper<City>>(new ResponseHelper<City>(message), HttpStatus.NOT_FOUND);
//		}
//
//		cityRepository.delete(id);
//		return new ResponseEntity<ResponseHelper<City>>(HttpStatus.NO_CONTENT);
//	}

	

}