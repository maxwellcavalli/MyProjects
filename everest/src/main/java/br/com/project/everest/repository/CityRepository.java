package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.City;
import br.com.project.everest.repository.base.CrudRepository;

@RepositoryRestResource
public interface CityRepository extends CrudRepository<City>{
	
	@Query(value = "select city "
			+ "       from City city "
			+ "      inner join fetch city.state state "
			+ "      where upper(city.name) like upper(:name) "
			+ "      order by city.name ",
			countQuery="select count(city) "
					+ "       from City city "
					+ "      where upper(city.name) like upper(:name) ")
	Page<City> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
	
}