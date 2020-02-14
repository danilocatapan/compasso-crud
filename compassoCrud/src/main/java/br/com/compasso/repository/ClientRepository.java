package br.com.compasso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compasso.data.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query("SELECT c FROM Client c WHERE c.id = ?1")
	Client myFindById(Long id);

	@Query("SELECT c FROM Client c WHERE c.fullName = ?1")
	List<Client> findByName(String name);

}