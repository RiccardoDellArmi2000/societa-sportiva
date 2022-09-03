package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Allenatore;
import it.uniroma3.siw.spring.model.Pacchetto;

@Repository
public interface PacchettoRepository extends CrudRepository<Pacchetto, Long> {
	
	public boolean existsByNome(String nome);
	
	public List<Pacchetto> findAllByAllenatore(Allenatore allenatore);
}
