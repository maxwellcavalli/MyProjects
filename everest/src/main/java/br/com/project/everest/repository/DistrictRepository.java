package br.com.project.everest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.project.everest.domain.District;

@RepositoryRestResource
public interface DistrictRepository extends PagingAndSortingRepository<District, Long>{
	
	District findById(@Param("id") Long id);
	
	District findByName(@Param("name") String name);
	
	@Query(value = "select district "
			+ "       from District district "
			+ "      inner join fetch district.city city "
			+ "      inner join fetch city.state state"
			+ "      where upper(district.name) like upper(:name) "
			+ "      order by district.name, city.name ",
			countQuery="select count(district) "
					+ "   from District district "
					+ "  inner join district.city city "
					+ "  inner join city.state state" 
					+ "  where upper(district.name) like upper(:name) ")
	Page<District> findByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
	
}