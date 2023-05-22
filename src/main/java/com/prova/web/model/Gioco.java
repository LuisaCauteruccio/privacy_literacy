package com.prova.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
 * Entity che descrive i giochi
 */
@Entity
public class Gioco {
	
	@Id
	private String idGioco;
	private String titolo;
	private String descrizione;
	
	
	public Gioco() {
		
	}


	public Gioco(String idGioco, String titolo, String descrizione) {
		super();
		this.idGioco = idGioco;
		this.titolo = titolo;
		this.descrizione = descrizione;
	}


	public String getIdGioco() {
		return idGioco;
	}


	public void setIdGioco(String idGioco) {
		this.idGioco = idGioco;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {
		return "Gioco [idGioco=" + idGioco + ", titolo=" + titolo + ", descrizione=" + descrizione + "]";
	}
	
	

}
