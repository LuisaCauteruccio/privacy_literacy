package com.prova.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
/*
 * Entity che descrive l'utente
 */
@Entity
public class Utente {
	
	@Id
	private String email;
	private String password;
	
	private String nome;
	private String cognome;
	private String dataNascita;
	
	private int punteggio;

	private String livello;
	
	public Utente() {
		
	}

	
	public Utente(String email, String password, String nome, String cognome, String dataNascita, int punteggio,String livello) {
		super();
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.punteggio=punteggio;
		this.livello=livello;
		
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}


	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	public int getPunteggio() {
		return punteggio;
	}
	
	public void setLivello(String livello) {
		this.livello = livello;
	}
	
	public String getLivello() {
		return livello;
	}


	@Override
	public String toString() {
		return "Utente [email=" + email + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", punteggio=" + punteggio + ", livello=" + livello + "]";
	}


	





	

	
	
	
	
	

}
