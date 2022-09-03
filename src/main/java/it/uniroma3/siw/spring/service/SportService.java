package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Accessorio;
import it.uniroma3.siw.spring.model.Sport;
import it.uniroma3.siw.spring.repository.SportRepository;

@Service
public class SportService {
	
	@Autowired
	private SportRepository sportRepository;
	
	@Transactional
	public void save(Sport sport) {
		this.sportRepository.save(sport);
	}
	
	@Transactional
	public void saveAll(List<Sport> sport) {
		this.sportRepository.saveAll(sport);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.sportRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Sport sport) {
		this.sportRepository.delete(sport);
	}
	
	@Transactional
	public Sport findById(Long id) {
		return sportRepository.findById(id).get();
	}
	
	@Transactional
	public List<Sport> findAll() {
		List<Sport> sport = new ArrayList<>();
		for(Sport s : sportRepository.findAll())
			sport.add(s);
		return sport;
	}
	
	@Transactional
	public boolean alreadyExists(Sport sport) {
		return this.sportRepository.existsByNome(sport.getNome());
	}
	
	@Transactional
	public void addAccessorio(Sport sport, Accessorio accessorio) {
		sport.addAccessorio(accessorio);
		sportRepository.save(sport);
	}
	
	@Transactional
	public void removeAccessorioFromSport(Sport sport, Accessorio accessorio) {
		sport.removeAccessorio(accessorio);
	}
	
	@Transactional
	public List<Sport> findSportNonNelPacchetto(List<Sport> sportPresenti) {
		List<Sport> sport = this.findAll();
		for(Sport s : sportPresenti)
			sport.remove(s);
		return sport;
	}
}
