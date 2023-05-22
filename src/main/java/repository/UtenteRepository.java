package repository;

import org.springframework.data.repository.CrudRepository;

import com.prova.web.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, String> {

}
