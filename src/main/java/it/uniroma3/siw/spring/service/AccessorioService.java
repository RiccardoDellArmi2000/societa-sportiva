package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Accessorio;
import it.uniroma3.siw.spring.repository.AccessorioRepository;

@Service
public class AccessorioService {
	
	@Autowired
	private AccessorioRepository accessorioRepository;
	
	@Transactional
	public void save(Accessorio accessorio) {
		this.accessorioRepository.save(accessorio);
	}
	
	@Transactional
	public void saveAll(List<Accessorio> accessori) {
		this.accessorioRepository.saveAll(accessori);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.accessorioRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Accessorio accessorio) {
		this.accessorioRepository.delete(accessorio);
	}
	
	@Transactional
	public Accessorio findById(Long id) {
		return this.accessorioRepository.findById(id).get();
	}
	
	@Transactional
	public List<Accessorio> findAll() {
		List<Accessorio> accessori = new ArrayList<>();
		for(Accessorio a : this.accessorioRepository.findAll())
			accessori.add(a);
		return accessori;
	}
	
	@Transactional
	public boolean alreadyExists(Accessorio accessorio) {
		return this.accessorioRepository.existsByNome(accessorio.getNome());
	}
}
