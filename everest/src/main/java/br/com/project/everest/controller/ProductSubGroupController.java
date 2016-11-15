package br.com.project.everest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.everest.controller.base.CrudController;
import br.com.project.everest.domain.ProductSubGroup;
import br.com.project.everest.repository.ProductSubGroupRepository;
import br.com.project.everest.repository.base.CrudRepository;

@RestController
@RequestMapping(value = { "/productSubGroup" })
public class ProductSubGroupController extends CrudController<ProductSubGroup> {

	@Autowired
	ProductSubGroupRepository productSubGroupRepository;

	@Override
	public ModelAndView getView() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Product Sub Group");
		model.setViewName("productSubGroup");
		return model;
	}

	@Override
	public CrudRepository<ProductSubGroup> getRepository() {
		return productSubGroupRepository;
	}

	@Override
	public void beforeSave(ProductSubGroup t) {
	}

	@Override
	public void validationCreate(ProductSubGroup t) throws Exception {
		if (t.getProductGroup() == null || t.getProductGroup().getId() == null
				|| t.getProductGroup().getId().longValue() == 0) {
			throw new Exception("Invalid Product Group");
		}
	}

}