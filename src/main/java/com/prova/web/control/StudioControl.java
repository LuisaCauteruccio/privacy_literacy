package com.prova.web.control;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.prova.web.model.Studio;

import repository.StudioRepository;
/*
 * Controller dello studio con sola implementazione dei metodi crud
 */
@Controller
@RequestMapping("/studio")
public class StudioControl {
	
	@Autowired
	private StudioRepository srep;
	
	@ResponseBody
	@GetMapping("/prova")
	public String prova() {
		return "Control Studio funziona";
	}
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		
		Studio s= new Studio();
		s.setIdStudio("S001");
		s.setTitolo("Argomento");
		s.setDescrizione("Si tratta di un argomento di studio");
		
		srep.save(s);
		return "argomento di studio aggiunto";
	}

	
	@ResponseBody
	@GetMapping("/find")
	public String findById() {
		
		Optional<Studio> stud=srep.findById("S002");
		return stud.get().getTitolo();
	}
	@ResponseBody
	@GetMapping("/update")
	public String update() {
		
		Studio s = srep.findById("S003").get();
		//s.setIdStudio("S002");
		s.setDescrizione("afefad");
		s.setTitolo("afdfs");
		
		srep.save(s);
		 return "argomento di studio aggiornato";
	}
	
	@ResponseBody
	@GetMapping("/all")
	public String findAll() {
		Iterable<Studio> listaStudio=srep.findAll();
		listaStudio.forEach((Studio s)->{
			System.out.println(s.getIdStudio());
		});
		
		return "ecco la lista di tutti gli studi ";
	}
	@ResponseBody
	@GetMapping("/delete")
	public String delete() {
		
		Studio s = srep.findById("S004").get();
		srep.delete(s);
		return "studio eliminato";
	}

}
