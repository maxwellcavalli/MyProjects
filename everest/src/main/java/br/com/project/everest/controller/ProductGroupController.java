package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.ProductGroup;
import br.com.project.everest.repository.ProductGroupRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/productGroup" })
public class ProductGroupController extends CrudController<ProductGroup> {

	@Autowired
	ProductGroupRepository productGroupRepository;

	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Product Group");
		model.setViewName("productGroup");
		return model;
	}

	@Override
	public CrudRepository<ProductGroup> getRepository() {
		return productGroupRepository;
	}

	@Override
	public void beforeSave(ProductGroup t) {
	}

	@Override
	public void validationCreate(ProductGroup t) throws Exception {
	}
}