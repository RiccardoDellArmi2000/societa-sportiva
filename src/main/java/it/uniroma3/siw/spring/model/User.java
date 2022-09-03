package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users") // cambiamo nome perchè in postgres "user" è una parola riservata
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	public User() {}
	
	public User(String nome, String cognome) {
		this.cognome = cognome;
		this.nome = nome;
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

	@Override
	public int hashCode() {
		return this.getNome().hashCode() +
				this.getCognome().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(o.getClass() != User.class)
			return false;
		User that = (User)o;
		return this.getNome().equals(that.getNome()) &&
				this.getCognome().equals(that.getCognome());
	}
	
	@Override
	public String toString() {
		return this.getNome() + " " + this.getCognome();
	}
}
