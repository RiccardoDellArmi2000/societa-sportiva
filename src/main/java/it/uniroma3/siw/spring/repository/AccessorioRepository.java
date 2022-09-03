package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Accessorio;

@Repository
public interface AccessorioRepository extends CrudRepository<Accessorio, Long> {
		
	public boolean existsByNome(String nome);
}
