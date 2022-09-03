package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "cognome", "nazionalita"}))
public class Allenatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String nazionalita;

	@OneToMany(mappedBy = "allenatore", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private List<Pacchetto> pacchetti;

	public Allenatore(String nome, String cognome, String nazionalita, List<Pacchetto> pacchetti) {
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.pacchetti = pacchetti;
	}
	
	public Allenatore() {
		this.pacchetti = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Pacchetto> getPacchetti() {
		return pacchetti;
	}

	public void setPacchetti(List<Pacchetto> pacchetti) {
		this.pacchetti = pacchetti;
	}
	

	@Override
	public int hashCode() {
		return this.getNome().hashCode() +
				this.getCognome().hashCode() + 
				this.getNazionalita().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass() != Allenatore.class)
			return false;
		Allenatore that = (Allenatore)o;
		return this.getNome().equals(that.getNome()) &&
				this.getCognome().equals(that.getCognome()) &&
				this.getNazionalita().equals(that.getNazionalita());
	}
	
	@Override
	public String toString() {
		return this.getNome() + " " + this.getCognome();
	}

	public void addPacchetto(Pacchetto pacchetto) {
		this.pacchetti.add(pacchetto);
	}

	public void removePacchetto(Pacchetto pacchetto) {
		this.pacchetti.remove(pacchetto);
	}
}
