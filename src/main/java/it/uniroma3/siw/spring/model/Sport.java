package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Sport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descrizione;
	
	
	
	public Sport() {
	}
	
	public Sport(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass() != Sport.class)
			return false;
		Sport that = (Sport)o;
		return this.getNome().equals(that.getNome());
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}

}
