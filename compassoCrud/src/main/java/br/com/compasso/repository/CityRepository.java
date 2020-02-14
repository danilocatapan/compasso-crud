package br.com.compasso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compasso.data.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Query("SELECT c FROM City c WHERE c.name = ?1")
	List<City> findByName(String name);
	
	@Query("SELECT c FROM City c WHERE c.state = ?1")
	List<City> findByState(String name);

}