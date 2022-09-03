package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Allenatore;
import it.uniroma3.siw.spring.model.Pacchetto;
import it.uniroma3.siw.spring.repository.AllenatoreRepository;

@Service
public class AllenatoreService {
	
	@Autowired
	private AllenatoreRepository allenatoreRepository;
	
	@Transactional
	public void save(Allenatore allenatore) {
		this.allenatoreRepository.save(allenatore);
	}
	
	@Transactional
	public void saveAll(List<Allenatore> allenatori) {
		this.allenatoreRepository.saveAll(allenatori);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.allenatoreRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Allenatore allenatore) {
		this.allenatoreRepository.delete(allenatore);
	}
	
	@Transactional
	public Allenatore findById(Long id) {
		return this.allenatoreRepository.findById(id).get();
	}
	
	@Transactional
	public List<Allenatore> findAll() {
		List<Allenatore> allenatori = new ArrayList<>();
		for(Allenatore a : this.allenatoreRepository.findAll())
			allenatori.add(a);
		return allenatori;
	}
	
	@Transactional
	public boolean alreadyExists(Allenatore allenatore) {
		return this.allenatoreRepository.existsByNomeAndCognomeAndNazionalita
				(allenatore.getNome(), allenatore.getCognome(), allenatore.getNazionalita());
	}
	
	@Transactional
	public void addPacchetto(Allenatore allenatore, Pacchetto pacchetto) {
		allenatore.addPacchetto(pacchetto);
		this.allenatoreRepository.save(allenatore);
	}
	
	@Transactional
	public void removePacchettoFromAllenatore(Allenatore allenatore, Pacchetto pacchetto) {
		allenatore.removePacchetto(pacchetto);
		this.allenatoreRepository.delete(allenatore);
	}
	
	@Transactional
	public Allenatore createAllenatore() {
		return new Allenatore();
	}
}
