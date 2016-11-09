package br.com.project.everest.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.everest.domain.City;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class CityService {

	@Autowired
	EntityManager entityManager;
	
	public Page<City> findByNameLikeIgnoreCase(String name, Pageable pageable){
		
		return null;
	}
	
	
	
	
}
