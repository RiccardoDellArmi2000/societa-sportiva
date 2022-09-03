package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Allenatore;

@Repository
public interface AllenatoreRepository extends CrudRepository<Allenatore, Long> {
	
	public boolean existsByNomeAndCognomeAndNazionalita(String nome, String cognome, String nazionalita);
}
