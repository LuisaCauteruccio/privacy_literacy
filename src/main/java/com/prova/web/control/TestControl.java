package com.prova.web.control;



import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prova.web.model.Domanda;

import com.prova.web.model.Test;
import com.prova.web.model.Utente;

import jakarta.servlet.http.HttpServletRequest;

import repository.DomandaRepository;
import repository.TestRepository;
import repository.UtenteRepository;
/*
 * 1. TOGLIERE RESPONSE BODY E AGGIUSTARE SEGUENDO I COMMENTI
 * se la lista  e vuota, se è l'ultima domanda 
 * aggiornare punteggio
 */
@Controller
@RequestMapping("/test")
public class TestControl {

	@Autowired
	private TestRepository testRep; //oggetto test repository

	@Autowired
	private DomandaRepository domRep; //oggetto domanda repository

	@Autowired
	private UtenteRepository utRep; //oggetto utente repository


	//questo lo possiamo usare se vogliamo mostrare il test principale all'amministratore NON SO SE SERVE
	@GetMapping("/testPrincipale") //test che mostra solo le domande che hanno 
	public ModelAndView testPrincipale() {

		System.out.println("sono in test princiaple con model and view");


		ArrayList<Domanda> listaTP= domRep.findByIdTest("TP");


		for(Domanda d : listaTP) {


			System.out.println("domanda tp" +d.getIdTest()+" "+d.getTesto());
		}



		return new ModelAndView("testPrincipale2","listaDomande",listaTP); 
	}

	/*
	 * metodo get che lancia il test principale, mostrando le domande che appartengono al test principale il cui idTest e TP
	 */
	@GetMapping("/tp")
	public ModelAndView tp(HttpServletRequest request) {

		System.out.println("sono in TP"); 
		ArrayList<Domanda> listaTP= domRep.findByIdTest("TP");//cerco le domande il cui idTest è TP


		//scorro le domande
		for(Domanda d : listaTP) {
			System.out.println("domanda tp"+" "+d.getIdTest()+" "+d.getTesto());


		}//mostro le domade all'utente
		return new ModelAndView("tp","listaDomande",listaTP);



	}



	//metodo post che prende le risposte inserite dall'utente controlla se la risposta 
	//è corretta o meno, controlla il punteggio e dal punteggio ricava il livello
	@PostMapping("/rispDom")
	public String rispDom (@ModelAttribute("listaDomande") Domanda dom, HttpServletRequest request, Model m) {

		//mi prendo la sessione dell'utente
		Utente utente=(Utente) request.getSession().getAttribute("utenteSessione");

		int punteggio=0;
		
		ArrayList<Domanda>listaTP=domRep.findByIdTest("TP");

		System.out.println("Sono nel metodo post");
		for(Domanda d : listaTP) {

			if(d.getIdDomanda()==0) {

				String az=request.getParameter("azioneRisposta");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);

				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			if(d.getIdDomanda()==1) {

				String az =request.getParameter("azione");



				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

			if(d.getIdDomanda()==2) {


				String az=request.getParameter("azioneId2");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

			if(d.getIdDomanda()==3) {


				String az=request.getParameter("azioneId3");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

			if(d.getIdDomanda()==4) {

				String az=request.getParameter("azioneId4");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}

			}

			if(d.getIdDomanda()==5) {


				String az=request.getParameter("azioneId5");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}

			}

			if(d.getIdDomanda()==6) {


				String az=request.getParameter("azioneId6");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

			
			
			if(d.getIdDomanda()==7) {


				String az=request.getParameter("azioneId7");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

			if(d.getIdDomanda()==8) {

				String az=request.getParameter("azioneId8");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

			if(d.getIdDomanda()==9) {


				String az=request.getParameter("azioneId9");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==10) {

				String az=request.getParameter("azioneId10");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==11) {

				String az=request.getParameter("azioneId11");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			if(d.getIdDomanda()==12) {

				String az=request.getParameter("azioneId12");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			if(d.getIdDomanda()==13) {

				String az=request.getParameter("azioneId13");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			if(d.getIdDomanda()==14) {

				String az=request.getParameter("azioneId14");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==15) {

				String az=request.getParameter("azioneId15");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==16) {

				String az=request.getParameter("azioneId16");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==17) {

				String az=request.getParameter("azioneId17");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==18) {

				String az=request.getParameter("azioneId18");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==19) {

				String az=request.getParameter("azioneId19");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==20) {

				String az=request.getParameter("azioneId20");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			if(d.getIdDomanda()==21) {

				String az=request.getParameter("azioneId21");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==22) {

				String az=request.getParameter("azioneId22");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==23) {

				String az=request.getParameter("azioneId23");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==24) {

				String az=request.getParameter("azioneId24");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}
			
			if(d.getIdDomanda()==25) {

				String az=request.getParameter("azioneId25");

				System.out.println("Sono nella domanda con id "+d.getIdDomanda()+" il valore della risposta scelta e: "+az);
				if(az.equals(d.getRisposta_corretta())) {
					System.out.println("la risposta e corretta");
					punteggio++;
				}else {
					System.out.println("la risposta e sbagliata");
				}
			}

		}

		System.out.println("L'utente "+utente.toString()+" "+" ha ottenuto un punteggio e: "+punteggio);
		utente.setPunteggio(punteggio);
		System.out.println("SETTO IL PUNTEGGIO "+utente.getPunteggio());
		//salvo l'utente




		//calcolo in base al punteggio il livello dell'utente
		//if((punteggio==1)||(punteggio==2)||(punteggio==3)||(punteggio==4)) {
		if(punteggio<=11) {
			
			System.out.println("il tuo livello di preparazione e' basso");
			String livello="Basso";
			utente.setLivello(livello);

		}else if((punteggio==12)||(punteggio==13)||(punteggio==14)||(punteggio==15)||(punteggio==16)||(punteggio==17)) {
			System.out.println("il tuo livello di preparazione e medio");
			String livello="Medio";
			utente.setLivello(livello);
		}else if((punteggio==18)||(punteggio==19)||(punteggio==20)||(punteggio==21)||(punteggio==22)||(punteggio==23)||(punteggio==24)||(punteggio==25)) {
			System.out.println("il tuo livello di preparazione e alto");
			String livello="Alto";
			utente.setLivello(livello);
		}

		utente=utRep.save(utente); 
		return "testEffettuato";
	}
	
	/*
	 * metodo che permette dalla sezione test di ritornare alla home
	 */
	@GetMapping("/home")  
	public String testHome() {
		return "redirect:/home/home";
	}






	@GetMapping("/tp2")
	public String getPr() {
		return "tp2";
	}
	@ResponseBody//perchè non c'è la view
	@GetMapping("/add")
	public String add() {

		//	ArrayList<Domanda> dom;
		//vedere come inserire le domande
		Test t = new Test();
		t.setIdTest("001");
		//t.setLivello(0);
		t.setTitolo("Malware");

		testRep.save(t);

		return "test aggiunto";
	}

	@ResponseBody
	@GetMapping("/getId")
	public String getById() {
		Optional<Test> test=testRep.findById("0");

		return test.get().getTitolo();

	}

	@ResponseBody
	@GetMapping("/all")
	public String getAll() {
		Iterable<Test> listaTest=testRep.findAll();

		listaTest.forEach((Test t)->{
			System.out.println(t.getIdTest()+t.getTitolo());

		});
		return "lista dei test";

	}
	//aggiungere cerca per livello prima da interfaccia e poi qui
	@ResponseBody
	@GetMapping("/prova")
	public String prova() {


		System.out.println("funziona");

		return " Controller test funziona";

	}

	@ResponseBody
	@GetMapping("/delete")
	public String delete() {

		Optional<Test> test = testRep.findById("0");//aggiustare qui perchè non prende metodo, mentre su utente si
		testRep.delete(test.get());//questo serve per far funzionare l'optional
		return "test eliminato";
	}



	@ResponseBody
	@GetMapping("update")
	public String update() {

		Test t= testRep.findById("1").get();
		t.setIdTest("11");
		//t.setPunteggio(1);
		//	t.setLivelloPreparazione(3);
		t.setTipo("principale");
		t.setTitolo("titolo del test");

		testRep.save(t);
		return "test aggiornato";
	}
}




