package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.Customer;
import br.com.project.everest.domain.CustomerContact;
import br.com.project.everest.repository.CustomerRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/customer" })
public class CustomerController extends CrudController<Customer>{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Customer");
		model.setViewName("customer");
		return model;
	}

	@Override
	public CrudRepository<Customer> getRepository() {
		return customerRepository;
	}

	@Override
	public void beforeSave(Customer t) {
		for (CustomerContact c : t.getCustomerContacts()){
			c.setCustomer(t);
		}
	}

	@Override
	public void validationCreate(Customer t) throws Exception {
		if (t.getDistrict() == null || t.getDistrict().getId() == null || t.getDistrict().getId().longValue() == 0){
			throw new Exception("Invalid District");
		}
	}
}