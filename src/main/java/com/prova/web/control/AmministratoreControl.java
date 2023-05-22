package com.prova.web.control;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.prova.web.model.Domanda;
import com.prova.web.model.Gioco;
import com.prova.web.model.Studio;
import com.prova.web.model.Test;
import com.prova.web.model.Utente;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.DomandaRepository;
import repository.GiocoRepository;
import repository.StudioRepository;
import repository.TestRepository;
import repository.UtenteRepository;
/*
 * Control relativo all'amministratore e tutte le operazioni che può fare
 * - Visualizzare o eliminare un utente
 * - Visualizzare, pubblicare, modificare un nuovo studio, test, domanda di un test o un gioco
 */
@Controller
@RequestMapping("/admin")
public class AmministratoreControl {

	
	@Autowired
	private UtenteRepository utRep; // oggetto dell'interfaccia repository dell'utente
	
	@Autowired
	private GiocoRepository giocoRep; //oggetto dell'interfaccia repository dei giochi

	
	@Autowired
	private StudioRepository studioRep; //oggetto dell'interfaccia repository di studio
	
	@Autowired
	private TestRepository testRep; //oggetto dell'interfaccia repository di un test
	
	@Autowired
	private DomandaRepository domRep; //oggetto dell'interfaccia repository delle domande

	//metodo che permette all'amministratore di visualizzare gli utenti
	@GetMapping("/visualUtenti")
	public ModelAndView visualizza(@ModelAttribute("listaUtenti") Utente utente,HttpServletRequest request, HttpServletResponse resp) {

		System.out.println("sono nel visual utenti get");

		Iterable<Utente> utenti= utRep.findAll();


		utenti.forEach((Utente u)->{
			System.out.println(u.getEmail()+" "+u.getCognome()+" "+u.getNome());

		});
		
		;
		return new ModelAndView("suAmm","listaUtenti", utenti);
	}
	
	//metodo che permette all'amministratore di visualizzare i giochi 
	@GetMapping("/visualGioco")
	public ModelAndView visualizzaGioco(@ModelAttribute("listaGiochi") Gioco gioco) {
	
		ArrayList<Gioco> giochi=(ArrayList<Gioco>) giocoRep.findAll();
		System.out.println("Lista giochi: ");
		for(Gioco g : giochi) {
			System.out.println("Gioco: "+g.getIdGioco()+" "+g.getTitolo()+" "+g.getDescrizione());
		}
		return new ModelAndView("sgAmm","listaGiochi",giochi);
	} 
	
	//metodo che permette all'amministratore di visualizzare gli argomenti di studio
	@GetMapping("/visualStudio")
	public ModelAndView visualizzaStudio(@ModelAttribute("listaStudio") Studio studio) {
		
		ArrayList<Studio> argomenti= (ArrayList<Studio>) studioRep.findAll(); 
		System.out.println("Lista studio: ");
		for(Studio s : argomenti) {
			System.out.println("Argomento di studio: "+s.getIdStudio()+" "+s.getTitolo()+" "+s.getDescrizione());
		}
		return new ModelAndView("ssAmm","listaStudio",argomenti);
	}
	
	//metodo che permette all'amministratore di visualizzare i test
	@GetMapping("/visualTest")
	public ModelAndView visualizzaTest() {
		ArrayList<Test> listaTest = (ArrayList<Test>) testRep.findAll();
		System.out.println("Lista test: ");
		for(Test t : listaTest) {
			System.out.println("Test: "+t.getIdTest()+" "+t.getTitolo()+" "+t.getTipo());
		}
		
		return new ModelAndView("stAmm","listaTest",listaTest);
	}
	
	//metodo che permette all'amministratore di visualizzare le domande
	@GetMapping("/visualDomande")
	public ModelAndView visualizzaDomande() {
		
		ArrayList<Domanda> listaDom=(ArrayList<Domanda>) domRep.findAll();
		System.out.println("Lista domande: ");
		for(Domanda d : listaDom) {
			System.out.println("Domanda: "+d.getIdDomanda()+" "+d.getIdTest()+" "+d.getTesto());
		}
		return new ModelAndView("sdAmm","listaDomande",listaDom);
	}
	@GetMapping("/cerca")
	public String cerca(HttpServletRequest request) {
		
		
		return "cerca";
	}
	
	//metodo che permette di all'amministratore di eliminare un utente
	@PostMapping("/cercaUtente")
	public String cercaUtente(HttpServletRequest request) {
		
		System.out.println("sono in cercaUtente");
		String email=request.getParameter("email");
		System.out.println(email);
		Utente utente =utRep.findById(email).get();
		System.out.println("Utente cerca con findById: "+utente);
		utRep.delete(utente);
		return "redirect:/admin/visualUtenti";
	}
	
	//metodo get che permette di inserire una nuova domanda
	@GetMapping("/nuovaDomanda")
	public ModelAndView nuovaDomanda() {
		return new ModelAndView("nuovaDomanda","dom",new Domanda());
	}
	
	//metodo post che permette di confermare l'inserimento di una nuova domanda
	@PostMapping("/addDomanda")
	public String addDomanda(@ModelAttribute("dom") Domanda domanda, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(domanda.getIdDomanda());
		System.out.println(domanda.getTesto());
		System.out.println(domanda.getRisposta1());
		System.out.println(domanda.getRisposta2());
		System.out.println(domanda.getRisposta3());
		System.out.println(domanda.getRisposta_corretta());
		System.out.println(domanda.getIdTest());
		
		//sessione domande
		domRep.save(domanda);
		return "redirect:/admin/visualDomande";
	}
	
	//metodo get che permette di inserire un nuovo gioco
	@GetMapping("/nuovoGioco")
	public ModelAndView nuovoGioco() {
		return new ModelAndView("nuovoGioco","gioco",new Gioco());
	}
	
	//metodo post per inserire un nuovo gioco
	@PostMapping("/addGioco")
	public String addGioco(@ModelAttribute("gioco") Gioco gioco,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(gioco.getIdGioco());
		System.out.println(gioco.getTitolo());
		System.out.println(gioco.getDescrizione());
		System.out.println("creo la sessione dei giochi");
		
		HttpSession giocoSessione=request.getSession();
		giocoSessione.setAttribute("giocoSessione", giocoSessione);
		System.out.println("la sessione e:"+" "+giocoSessione.getId());
		
		giocoRep.save(gioco);
		return "redirect:/admin/visualGioco";
		//return new ModelAndView("homeAmm","gioco",gioco);//redirect dopo proviamo
	}
	
	//metodo get che permette di inserire un  nuovo test
	@GetMapping("/nuovoTest")
	public ModelAndView nuovoTest() {
		return new ModelAndView("nuovoTest","test", new Test());
	}
	
	//metodo post che permette di inserire in modo effettivo un nuovo test
	@PostMapping("/addTest")
	public String addTest(@ModelAttribute("test") Test test,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(test.getIdTest());
		System.out.println(test.getTitolo());
		System.out.println(test.getTipo());

		
		testRep.save(test);
		return "redirect:/admin/visualTest";
		//return new ModelAndView("homeAmm","gioco",gioco);//redirect dopo proviamo
	}
	
	//metodo get che permette di cercare un gioco che poi andrà eliminato
	@GetMapping("/cercaGioco")
	public String cercaGioco() {
		return "cercaGioco";
	}
	//metodo che permette di all'amministratore di eliminare un gioco
	@PostMapping("/cercaGame")//elimina game
	public String cercaGame(HttpServletRequest request) {
		
		System.out.println("sono in cercaGioco");
		String id=request.getParameter("idGioco");
		System.out.println("id Gioco: "+id);
		Gioco gioco=giocoRep.findById(id).get();
		System.out.println("Gioco trovato: "+gioco);
		giocoRep.delete(gioco);
		return "redirect:/admin/visualGioco";
		
	}
	//metodo che lancia cercaTest
	@GetMapping("/cercaTest")
	public String cercaTest() {
		return "cercaTest";
	}
	//metodo che permette di all'amministratore di eliminare un test
	@PostMapping("/cercaTestPost")
	public String cercaTestPost(HttpServletRequest request) {
		
		System.out.println("sono in cercaTest");
		String id=request.getParameter("idTest");
		System.out.println("idTest: "+id);
		Test test=testRep.findById(id).get();
		System.out.println("Test trovato: "+test);
		testRep.delete(test);
		return "redirect:/admin/visualTest";
	}
	//metodo che lancia cerca domanda
	@GetMapping("cercaDomanda")
	public String cercaDomanda() {
		return "cercaDomanda";
	}
	
	//metodo che permette di all'amministratore di eliminare una domanda
	@PostMapping("/cercaDomandaPost")
	public String cercaDomandaPost(HttpServletRequest request) {
		System.out.println("sono in cerca domanda post");
		String id=request.getParameter("idDomanda");		
		System.out.println("id della domanda: "+id);
		int idDomanda=Integer.parseInt(id);
		Domanda dom= domRep.findById(idDomanda).get();
		domRep.delete(dom);
		return "redirect:/admin/visualDomande";
	}
	//metodo che permette di lanciare cercaStudio
	@GetMapping("/cercaStudio")
	public String cercaStudio() {
		return "cercaStudio";
	}
	
	//metodo che permette di all'amministratore di eliminare un argomento di studio
	@PostMapping("/cercaArgomento")
	public String cercaArgomento(HttpServletRequest request) {
		System.out.println("sono in cerca argomento post");
		String id=request.getParameter("idStudio");
		System.out.println("id studio: "+id);
		Studio s=studioRep.findById(id).get();
		studioRep.delete(s);
		
		return "redirect:/admin/visualStudio";
	}
	
	//metodo get che permette di lanciare la funzionalità cerca studio in modo da modificarlo
	@GetMapping("/cercaStudioModificare")
	public ModelAndView cercaStudioMod(Studio studio) {
		System.out.println("Sono in cerca studio da modificare");
		return new ModelAndView("cercaStudioModificare","cercaStudioMod",studio);
	}
	
	//metodo post che permette di modificare uno studio
	@PostMapping("/cercaStudioMod")
	public String cercaStudioMod(HttpServletRequest req) {
		System.out.println("sono in cercaStudioMod");
		
		String id=req.getParameter("idStudio");
		Studio studio=studioRep.findById(id).get();
		System.out.println(studio);
		String nuovotit=req.getParameter("titolo");
		String nuovaDe=req.getParameter("descrizione");
		studio.setIdStudio(studio.getIdStudio());
		studio.setTitolo(nuovotit);
		studio.setDescrizione(nuovaDe);
		studioRep.save(studio);
		
		return "redirect:/admin/visualStudio";
		
	}
	
	//metodo get che permette la funzionalità di cercare un gioco che poi va modificato
	@GetMapping("/cercaGiocodaModificare")
	public ModelAndView cercaModificare(Gioco gioco) {
		
		System.out.println("Sono in cerca gioco da modificare");
		
		return new ModelAndView("cercaGiocodaModificare","cercaMod",gioco);
	}
	
	//metodo get che permette la funzionalità di cercare un test che poi va modificato
	@GetMapping("/cercaTestModificare")
	public ModelAndView cercaTestModificare(Test test) {
		return new ModelAndView("cercaTestModificare","cercaTestModificare",test);
	}
	
	//metodo che permette di confermare la modifica di un test
	@PostMapping("/cercaTestMod")
	public String cercaTestMod(HttpServletRequest req) {
		System.out.println("sono in cercaStudioMod");
		
		String id=req.getParameter("idTest");
		Test test = testRep.findById(id).get();
		
		System.out.println(test);
		String nuovotit=req.getParameter("titolo");
		String nuovoTipo=req.getParameter("tipo");
		test.setIdTest(test.getIdTest());
		test.setTitolo(nuovotit);
		test.setTipo(nuovoTipo);
		testRep.save(test);
		
		return "redirect:/admin/visualTest";
		
	}
	
	//metodo get che permette la funzionalità di cercare una domanda che poi va modificato
	@GetMapping("/cercaDomandaModificare")
	public ModelAndView cercaDomandaModficare(Domanda domanda) {
		return new ModelAndView("cercaDomandaModificare","cercaDomMod",domanda);
	}
	
	//metodo che permette di confermare la modifica di una domanda
	@PostMapping("/cercaDomandaMod")
	public String cercaDomandaMod(HttpServletRequest req) {
		System.out.println("sono in cercaStudioMod");
		
		String id=req.getParameter("idDomanda");
		int idDomanda=Integer.parseInt(id);
		Domanda domanda=domRep.findById(idDomanda).get(); 
		//Studio studio=studioRep.findById(id).get();
		System.out.println(domanda);
		String nuovoTesto=req.getParameter("testo");
		String nuovaRisp1=req.getParameter("risposta1");
		String nuovaRisp2=req.getParameter("risposta2");
		String nuovaRisp3=req.getParameter("risposta3");
		String nuovaRispCorretta=req.getParameter("risposta_corretta");
		String idTestNuov=req.getParameter("idTest");
		
		
		domanda.setIdDomanda(domanda.getIdDomanda());
		domanda.setTesto(nuovoTesto);
		domanda.setIdTest(idTestNuov);
		domanda.setRisposta1(nuovaRisp1);
		domanda.setRisposta2(nuovaRisp2);
		domanda.setRisposta3(nuovaRisp3);
		domanda.setRisposta_corretta(nuovaRispCorretta);
		
		domRep.save(domanda);
		
		
		return "redirect:/admin/visualDomande";
		
	}
	
	//metodo che permette di confermare la modifica del gioco
	@PostMapping("/cercaGameMod")
	public String cercaGameMod(HttpServletRequest req) {
		
		System.out.println("sono in cercaGameMod");
		
		String id=req.getParameter("idGioco");
		Gioco gioco=giocoRep.findById(id).get();
		System.out.println(gioco);
		String nuovotit=req.getParameter("titolo");
		String nuovaDe=req.getParameter("descrizione");
		gioco.setIdGioco(gioco.getIdGioco());
		gioco.setTitolo(nuovotit);
		gioco.setDescrizione(nuovaDe);
		giocoRep.save(gioco);
		
		return "redirect:/admin/visualGioco";
	}
	

	@GetMapping("/nuovoStudio")
	public String nuovoStudio() {
		return "nuovoStudio";
	}
	
	@PostMapping("/addStudio")
	public String addStudio(@ModelAttribute("studio") Studio studio,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(studio.getIdStudio());
		System.out.println(studio.getTitolo());
		System.out.println(studio.getDescrizione());

		
		studioRep.save(studio);
		return "redirect:/admin/visualStudio";
		
	}
	
	//metodo che riporta alla home
	 @GetMapping("/home")
		public String home() {
			return "redirect:/home/homeAmm";
		}

	

}