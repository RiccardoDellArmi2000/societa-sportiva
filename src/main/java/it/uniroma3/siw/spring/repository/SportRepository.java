package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Sport;

@Repository
public interface SportRepository extends CrudRepository<Sport, Long>{
	
	public boolean existsByNome(String nome);
}
