package com.prova.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
/*
 * Entity che descrive un test
 */
@Entity
public class Test {

	@Id
	private String idTest;  //T001
	private String titolo;
	private String tipo;
	
	
	
	public Test() {
		
	}


	public Test(String idTest, String titolo, String tipo) {
		super();
		this.idTest = idTest;
		this.titolo = titolo;
		this.tipo = tipo;
		
	}


	public String getIdTest() {
		return idTest;
	}


	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}




	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", titolo=" + titolo + ", tipo=" + tipo + ","+ "]";
	}


	
	
	
	

}
