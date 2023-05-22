package com.prova.web.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prova.web.model.Gioco;
import com.prova.web.model.Utente;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.GiocoRepository;
import repository.UtenteRepository;
// control per le funzionalità dell'utente
@Controller
@RequestMapping("/utente")
public class UtenteControl {

	@Autowired
	private UtenteRepository utRep; //oggetto di utente repository
	
	@Autowired
	private GiocoRepository giocoRep; //oggetto di gioco repository

 //metodo get che permette di lanciare il form della registrazione
	@GetMapping("/reg") 
	public ModelAndView reg() {
		return new ModelAndView("reg", "regi", new Utente());
	}
	
	
	//metodo post che permette la registrazione dell'utente
	@PostMapping("/registrati")
	public ModelAndView registrati(@ModelAttribute("regi") Utente utente,HttpServletRequest request, HttpServletResponse response) {

		System.out.println(utente.getEmail());
		System.out.println(utente.getPassword());
		System.out.println(utente.getDataNascita());
		
		System.out.println("creo la sessione dell'utente");
		
		//creo la sessione dell'utente
		HttpSession utenteSessione=request.getSession();
		utenteSessione.setAttribute("utenteSessione", utenteSessione);
		System.out.println("la sessione e:"+" "+utenteSessione.getId());
		
		//creo la sessione del test principale
		
		HttpSession testPriSessione=request.getSession();
		testPriSessione.setAttribute("testPriSessione", testPriSessione);
		System.out.println("la sessione del test principale:"+" "+testPriSessione.getId());
		



		utRep.save(utente);
		return new ModelAndView("login", "regi",utente);
	}
	

	//metodo che lancia il form del login
	@GetMapping("/nuovolog")
	public ModelAndView log() {

		return new ModelAndView("login","login", new Utente());
	}

	//metdo che controlla il login e effettua l'accesso dell'utente
	@PostMapping("/login")
	public String  login(@ModelAttribute("login") Utente utente, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email=request.getParameter("email");
		String password=request.getParameter("password");

		//controllo su email e password
		System.out.println("sono in /login");
		System.out.println(utente.getEmail());
		System.out.println(utente.getPassword());
		
		utente= utRep.findById(utente.getEmail()).get();
		System.out.println("Si tratta di"+utente.toString());
		
		
		//1. Controllo su amministratore
		if(utente.getEmail().equals("admin")&&(utente.getPassword().equals("admin"))) {
			System.out.println("admin entrato");
			//setto la sessione dell'amministratore
			request.getSession().setAttribute("capoSessione", utente);
			System.out.println("sessione dell amministratore: "+request.getSession().getId());
			
			
		}
		
		//2. Controllo su email e password
		if(utente.getEmail().equals(email)) {
			if(utente.getPassword().equals(password)) {
				
				response.setContentType("text/html;charset=ISO-8859-1");
				response.getWriter().write("successo");
				
				//istanziamo sessione dell'utente
				request.getSession().setAttribute("utenteSessione", utente);
				
				System.out.println("sessione dell utente"+utente);
				System.out.println("Session id"+" "+request.getSession().getId());
				
			
			}else {
				System.out.println("password sbagliata\n");
				response.setContentType("text/html;charset=ISO-8859-1");
				response.getWriter().write("passwordFailed");
			}
			
		}else {
			System.out.println("email sbagliata\n");
			response.setContentType("text/html;charset=ISO-8859-1");
			response.getWriter().write("notExists");
		}


		return "redirect:/home/home";
		
	}

	
	//metodo che permette all'utente di visualizzare il suo profilo
	@GetMapping("/profiloUtente") 
	
	public ModelAndView profilo ( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("sono in profilo Utente");
		
		Utente utente=(Utente) request.getSession().getAttribute("utenteSessione");
		System.out.println(utente.toString());
		
		return new ModelAndView("profilo2","login",utente);
	}

	//metodo  get che permette all'utente di modificare il suo profilo 
	@GetMapping("/modificaProfilo")
	public ModelAndView modProf(HttpServletRequest request, HttpServletResponse response ) {
		
		
		System.out.println("sono in modifica profilo get");
		
		Utente utente=(Utente) request.getSession().getAttribute("utenteSessione");
		
		System.out.println("Sessione dell'utente in modifica profilo"+utente);
		return new ModelAndView("modificaProfilo","datiModificati",utente);
	}
	
	
	//metodo post che conferma la modifica
	@PostMapping("/modifica")
	public ModelAndView modifica(@ModelAttribute("dm") Utente utente,HttpServletRequest request )throws IOException {

		System.out.println("sono in modifica profilo post");
		
	
		
		utente=(Utente) request.getSession().getAttribute("utenteSessione");
		System.out.println("Prendo la sessione dell'utente: "+utente.toString());
		
		System.out.println("cerco l'utente per email");
		
		utente= utRep.findById(utente.getEmail()).get();
		
	
		
		String nuovaPassword=request.getParameter("password");
		
		System.out.println("nuova password"+" "+nuovaPassword);
		
		utente.setEmail(utente.getEmail());
		utente.setPassword(nuovaPassword);
		utente.setNome(utente.getNome());
		utente.setCognome(utente.getCognome());
		utente.setDataNascita(utente.getDataNascita());
		
	
		
		
	
		utRep.save(utente);
		return new ModelAndView("profilo3", "dm",utente);
	}

	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		Utente u= new Utente();
		u.setNome("Biagio");
		u.setCognome("Antonacci");
		u.setDataNascita("1 Aprile 1000");
		u.setEmail("biagio.antonacci@gmail.com");
		u.setPassword("BIASINO");

		utRep.save(u);
		return "utente aggiunto"+" "+u.getEmail();
	}

	@ResponseBody
	@GetMapping("/all") //metodo che ritorna la lista di tutti gli utenti presenti sulla piattaforma
	public String getAll() {

		Iterable<Utente> utenti= utRep.findAll();


		utenti.forEach((Utente u)->{
			System.out.println(u.getEmail()+" "+u.getCognome()+" "+u.getNome());
			//System.out.println("sono nel for each");
		});

		return "lista di tutti gli utenti";
	}

	@ResponseBody //metodo che ritorna un utente per email
	@GetMapping("/email")
	public String getById() {
		Optional<Utente> ut= utRep.findById("tri@gmail.com");

		return ut.get().getNome();

	}

	@ResponseBody
	@GetMapping("/delete")
	public String delete() {
		//vedere se funziona
		Optional<Utente> utente=utRep.findById("tri@gmail.com");
		utRep.delete(utente.get());
		return "utente eliminato";
	}

	@ResponseBody
	@GetMapping("update")
	public String update() {

		Utente u = utRep.findById("uff@libero.com").get();
		u.setNome("Giovanni");
		u.setCognome("Miccoli");
		u.setPassword("nuovaPassword");
		u.setDataNascita("15 Marzo 2009");

		utRep.save(u);

		return "utente aggiornato";
	}

	@ResponseBody
	@GetMapping("/prova")
	public String prova() {

		return "Controller utente funziona";

	}
	


	@GetMapping("/eliminaProfilo")
	public String elimina(HttpServletRequest request) {
		
		Utente utente=(Utente) request.getSession().getAttribute("utenteSessione");
		System.out.println(utente);
		utRep.delete(utente);
		return "eliminaProfilo";
	}
	
	@GetMapping("/welcome")
	public String welcome(ModelMap model) {
		model.addAttribute("nome", "luisa");
		return "welcome";
	}
	
	//metodo che permette all'utente di ritornare alla home
	 @GetMapping("/home")
	public String home() {
		return "redirect:/home/home";
	}
	 
	
	

	//metodo che permette il logout
	@GetMapping("/logout")
	public String logout(HttpSession utenteSessione,HttpServletRequest request ) {
	
		System.out.println("sono in logout");
		utenteSessione=request.getSession();
		utenteSessione.invalidate();
		System.out.println("Ho effettuato il logout");
		 return "logout";
	}
	
	
	//non so che fa
	@GetMapping("/sezioneGiochi")
	public ModelAndView visualizzaGioco(@ModelAttribute("listaGiochi") Gioco gioco) {
			
			ArrayList<Gioco> giochi=(ArrayList<Gioco>) giocoRep.findAll();
			
			for(Gioco g : giochi) {
				System.out.println("Gioco: "+g.getIdGioco()+" "+g.getTitolo()+" "+g.getDescrizione());
			}
			return new ModelAndView("sezioneGiochi","listaGiochi",giochi);
		
		
	
	
	}
	





}
