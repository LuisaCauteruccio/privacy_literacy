package repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.prova.web.model.Domanda;

public interface DomandaRepository extends CrudRepository<Domanda,Integer> {

	public ArrayList<Domanda> findByIdTest(String idTest);
	
	

}
