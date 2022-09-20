package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Allenatore;
import it.uniroma3.siw.spring.model.Pacchetto;
import it.uniroma3.siw.spring.model.Sport;
import it.uniroma3.siw.spring.repository.PacchettoRepository;

@Service
public class PacchettoService {
	
	@Autowired
	private PacchettoRepository pacchettoRepository;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private AllenatoreService allenatoreService;
	
	@Transactional
	public void save(Pacchetto pacchetto, Allenatore allenatore) {
		pacchetto.setAllenatore(allenatore);
		allenatore.addPacchetto(pacchetto);
		pacchettoRepository.save(pacchetto);
	}
	
	@Transactional
	public void saveAll(List<Pacchetto> pacchetti) {
		this.pacchettoRepository.saveAll(pacchetti);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.pacchettoRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Pacchetto pacchetto) {
		this.pacchettoRepository.delete(pacchetto);
	}
	
	@Transactional
	public Pacchetto findById(Long id) {
		return this.pacchettoRepository.findById(id).get();
	}
	
	@Transactional
	public List<Pacchetto> findAll() {
		List<Pacchetto> pacchetti = new ArrayList<>();
		for(Pacchetto p : this.pacchettoRepository.findAll())
			pacchetti.add(p);
		return pacchetti;
	}
	
	@Transactional
	public boolean alreadyExists(Pacchetto pacchetto) {
		return this.pacchettoRepository.existsByNome(pacchetto.getNome());
	}
	
	@Transactional
	public List<Pacchetto> findAllByAllenatore(Allenatore allenatore) {
		List<Pacchetto> pacchetti = new ArrayList<>();
		for(Pacchetto p : this.pacchettoRepository.findAllByAllenatore(allenatore))
			pacchetti.add(p);
		return pacchetti;
	}
	
	@Transactional
	public void addSport(Pacchetto pacchetto, Sport sport) {
		pacchetto.addSport(sport);
		this.pacchettoRepository.save(pacchetto);
	}
	
	@Transactional
	public void removeSportFormPacchetto(Long idPacchetto, Long idSport) {
		Pacchetto pacchetto = this.findById(idPacchetto);
		Sport sport = this.sportService.findById(idSport);
		pacchetto.removeSport(sport);
		this.pacchettoRepository.save(pacchetto);
	}
	
	public List<Sport> findSportNotInPacchetto(Long idPacchetto) {
		Pacchetto pacchetto = this.findById(idPacchetto);
		return sportService.findSportNonNelPacchetto(pacchetto.getSport());
	}

	public Pacchetto createPacchetto() {
		return new Pacchetto();
	}

	@Transactional
	public void addSportToPacchetto(Long pacchettoId, Long sportId) {
		Pacchetto pacchetto = this.findById(pacchettoId);
		Sport sport = sportService.findById(sportId);
		pacchetto.addSport(sport);
		pacchettoRepository.save(pacchetto);
	}

	@Transactional
	public void removePacchetto(Long pacchettoId) {
		Pacchetto pacchetto = this.findById(pacchettoId);
		pacchetto.setSport(new ArrayList<>());
		allenatoreService.removePacchettoFromAllenatore(pacchetto.getAllenatore(), pacchetto);
		this.delete(pacchetto);
	}

	public Long getNumeroPacchetti() {
		return this.pacchettoRepository.count();
	}
}

