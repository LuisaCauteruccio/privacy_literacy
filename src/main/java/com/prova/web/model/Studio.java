package com.prova.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
 * Entity che descrive un argomento di studio
 */
@Entity
public class Studio {

	@Id
	private String idStudio;//SOO1D
	
	private String titolo;
	private String descrizione;
	
	public Studio() {
		
	}

	public Studio(String  idStudio, String titolo, String descrizione) {
		super();
		this.idStudio = idStudio;
		this.titolo = titolo;
		this.descrizione = descrizione;
	}

	public String getIdStudio() {
		return idStudio;
	}

	public void setIdStudio(String idStudio) {
		this.idStudio = idStudio;
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
		return "Studio [idStudio=" + idStudio + ", titolo=" + titolo + ", descrizione=" + descrizione + "]";
	}
	
	
	  
}

