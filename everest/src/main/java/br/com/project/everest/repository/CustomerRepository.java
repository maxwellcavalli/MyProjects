package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.Customer;
import br.com.project.everest.repository.base.CrudRepository;

@RepositoryRestResource(path="customerRepo", collectionResourceRel="d")
public interface CustomerRepository extends CrudRepository<Customer> {

	
	@Query(value = "select customer "
			+ "       from Customer customer "
			+ "      inner join fetch customer.district district "
			+ "      inner join fetch district.city city "
			+ "      inner join fetch city.state state "
			+ "      where upper(customer.name) like upper(:name) "
			+ "      order by customer.name ",
			countQuery="select count(customer) "
					+ "       from Customer customer "
					+ "      inner join customer.district district "
					+ "      inner join district.city city "
					+ "      inner join city.state state "
					+ "      where upper(customer.name) like upper(:name) ")
	Page<Customer> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
	
	
}