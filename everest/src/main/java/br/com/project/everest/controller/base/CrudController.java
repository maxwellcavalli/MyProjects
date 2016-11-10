package br.com.project.everest.controller.base;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.project.everest.domain.base.CrudDomain;
import br.com.project.everest.helper.ResponseHelper;
import br.com.project.everest.repository.base.CrudRepository;

public abstract class CrudController<T extends CrudDomain>  {

	public abstract ModelAndView getView();

	public abstract CrudRepository<T> getRepository();

	public abstract void beforeSave(T t);

	public abstract void validationCreate(T t) throws Exception;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView mainMethod() {
		ModelAndView model = getView();
		System.out.println("° ° ° ° welcomePage running. model = " + model);
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> getDomain(@PathVariable("id") long id) {
		try {
			System.out.println("Fetching Domain with id " + id);
			T domain = getRepository().findById(id);

			if (domain == null) {
				System.out.println("Domain with id " + id + " not found");
				return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<T>(domain, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseHelper<T>> createDomain(@RequestBody T domain, UriComponentsBuilder ucBuilder) {
		try {
			System.out.println("Creating domain " + domain.toString());
			validationCreate(domain);
			beforeSave(domain);

			domain = getRepository().save(domain);
			return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(domain), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseHelper<T>> updateDomain(@PathVariable("id") long id, @RequestBody T domain) {
		try {
			System.out.println("Updating Domain " + id);

			T currentDomain = getRepository().findById(id);
			if (currentDomain == null) {
				String message = "Domain with id " + id + " not found";
				System.out.println(message);

				return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(message), HttpStatus.NOT_FOUND);
			}

			beforeSave(domain);
			BeanUtils.copyProperties(currentDomain, domain);
			currentDomain.setId(id);
			
			currentDomain = getRepository().save(currentDomain);

			return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(currentDomain), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseHelper<T>> deleteDomain(@PathVariable("id") long id) {
		try {
			System.out.println("Fetching & Deleting Domain with id " + id);
			T domain = getRepository().findById(id);
			if (domain == null) {
				String message = "Unable to delete. Domain with id " + id + " not found";
				System.out.println(message);

				return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(message), HttpStatus.NOT_FOUND);
			}

			getRepository().delete(id);
			return new ResponseEntity<ResponseHelper<T>>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<ResponseHelper<T>>(new ResponseHelper<T>(e.getMessage()),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

}