package com.prova.web.control;

import org.springframework.stereotype.Controller;




import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

/*
 * classe che permette dalla home di muoversi fra le varie pagine o funzionalità
 */
@Controller
@RequestMapping("/home")
public class HomeControl {

//SEZIONE RELATIVA ALL'UTENTE
	
	
	@GetMapping("/v")
	public String visualUtente() {
		return "visualizzaUtente";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/sezioneGiochi")
	public String sezioneGiochi() {
		return "redirect:/utente/sezioneGiochi" ;
	}

	@GetMapping("/reg")
	public String formReg() {
		return "redirect:/utente/reg";
	}

	@GetMapping("/profilo")
	public String profiloUtente() {
		return "redirect:/utente/profiloUtente";
	}
	
	@GetMapping("/login")
	public String log()
	{
		return "redirect:/utente/nuovolog";
	}


	@GetMapping("sezioneTest") //sezione test dell'utente da navbar
	public String sezioneTest() {
		return "sezioneTest";
	}
	// SEZIONE RELATIVA ALL'ADMIN
	
	@GetMapping("homeAmm")//home amministratore
	public String homeAmministratore() {
		return "homeAmm";
	}


	@GetMapping("/testPrincipale")
	public String testPrincipale() {
		return "redirect:/test/testPrincipale";
	}

	@GetMapping("stAmm") //sezione test amministratore
	public String stAmm() {
		return "redirect:/admin/visualTest";
	}
	
	@GetMapping("suAmm") //sezione utenti amministratore
	public String suAmm() {
		return "redirect:/admin/visualUtenti";
	}
 
	
	@GetMapping("sgAmm") //sezione giochi amministratore
	public String sgAmm() {
		return "redirect:/admin/visualGioco";
	}
	
	@GetMapping("ssAmm") //sezione studio amministratore
	public String ssAmm() {
		return "redirect:/admin/visualStudio";
	}
	@GetMapping("visualizzaUtnete") //amministratore visualizza utente
	public String visualizzaUt() {
		return "visualizzaUtente";
	}
	
	@GetMapping("sdAmm") //sezione domande amministratore
	public String sdAmm() {
		return "redirect:/admin/visualDomande";
	}
	
	@GetMapping("/tp") //utente visualizza test principale dalla home
	public String testPrincipaleNuov() {
		return "redirect:/test/tp";
	}
	
	@GetMapping("/logout")  
	public String logout() {
		return "redirect:/utente/logout";
	}
 
	
	



}
