package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.Product;
import br.com.project.everest.repository.ProductRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/product" })
public class ProductController extends CrudController<Product> {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Product");
		model.setViewName("product");
		return model;
	}

	@Override
	public CrudRepository<Product> getRepository() {
		return productRepository;
	}

	@Override
	public void beforeSave(Product t) {
	}

	@Override
	public void validationCreate(Product t) throws Exception {
		if (t.getProductSubGroup() == null || t.getProductSubGroup().getId() == null
				|| t.getProductSubGroup().getId().longValue() == 0) {
			throw new Exception("Invalid Product Sub Group");
		}
	}

}