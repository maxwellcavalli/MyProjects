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

import br.com.project.everest.domain.District;
import br.com.project.everest.helper.ResponseHelper;
import br.com.project.everest.repository.DistrictRepository;

@RestController
public class DistrictController {

	@Autowired
	DistrictRepository districtRepository; 

	@RequestMapping(value = { "/district" }, method = RequestMethod.GET)
	public ModelAndView mainMethod() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "District");
		model.setViewName("district");
		
		System.out.println("° ° ° ° welcomePage running. model = " + model);
		return model;
	}

	@RequestMapping(value = "/district/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<District> getDomain(@PathVariable("id") long id) {
		System.out.println("Fetching Domain with id " + id);
		
		District domain = districtRepository.findById(id);
		if (domain == null) {
			System.out.println("Domain with id " + id + " not found");
			return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<District>(domain, HttpStatus.OK);
	}

	@RequestMapping(value = "/district/", method = RequestMethod.POST)
	public ResponseEntity<ResponseHelper<District>> createDomain(@RequestBody District domain, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating domain " + domain.getName());
		domain = districtRepository.save(domain);
		
		return new ResponseEntity<ResponseHelper<District>>(new ResponseHelper<District>(domain) , HttpStatus.CREATED);
	}

	@RequestMapping(value = "/district/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseHelper<District>> updateDomain(@PathVariable("id") long id, @RequestBody District domain) {
		System.out.println("Updating Domain " + id);

		District currentDomain = districtRepository.findById(id);
		if (currentDomain == null) {
			String message = "Domain with id " + id + " not found";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<District>>(new ResponseHelper<District>(message), HttpStatus.NOT_FOUND);
		}

		currentDomain.setName(domain.getName());
		currentDomain.setCity(domain.getCity());
		currentDomain = districtRepository.save(currentDomain);
		
		return new ResponseEntity<ResponseHelper<District>>(new ResponseHelper<District>(currentDomain), HttpStatus.OK);
	}

	@RequestMapping(value = "/district/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseHelper<District>> deleteDomain(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Domain with id " + id);

		District domain = districtRepository.findById(id); 
		if (domain == null) {
			String message = "Unable to delete. Domain with id " + id + " not found";
			System.out.println(message);
			
			return new ResponseEntity<ResponseHelper<District>>(new ResponseHelper<District>(message), HttpStatus.NOT_FOUND);
		}

		districtRepository.delete(id);
		return new ResponseEntity<ResponseHelper<District>>(HttpStatus.NO_CONTENT);
	}

}