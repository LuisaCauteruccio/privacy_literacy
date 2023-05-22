package com.prova.web.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prova.web.model.Gioco;


import repository.GiocoRepository;

@Controller
@RequestMapping("/gioco")
public class GiocoControl {

	
	@Autowired
	private GiocoRepository giocoRep;
	
	@ResponseBody
	@GetMapping("/prova")
	public String prova() {
		return "control gioco funziona";
	}
	

	@ResponseBody
	@GetMapping("/all")
	public String all() {
		Iterable<Gioco> giochi= giocoRep.findAll();
		giochi.forEach((Gioco g)->{
			
			System.out.println(g.getIdGioco());
		});
		
		return "tutti i giochi";
	}
	
	@ResponseBody
	@GetMapping("/find")
	public String getById() {
		Optional<Gioco> gioco=giocoRep.findById("G003");
	
		return gioco.get().getIdGioco();
	}
	
	@ResponseBody
	@GetMapping("/delete")
	public String delete() {
		Optional<Gioco> gioco = giocoRep.findById("G003");
		giocoRep.delete(gioco.get());
		return "gioco eliminato"+gioco.get().getIdGioco();
	}
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		
		Gioco g = new Gioco();
		g.setIdGioco("G007");
		g.setTitolo("Giochino");
		g.setDescrizione("Che bel gioco");
		
		giocoRep.save(g);
		return "gioco aggiunto"+" "+g.getIdGioco();
	}
	
	@ResponseBody
	@GetMapping("/update")
	public String update() {
		
		Gioco g = giocoRep.findById("G007").get();
		g.setTitolo("GAME");
		giocoRep.save(g);
		return "gioco aggiornato";
	}
}
