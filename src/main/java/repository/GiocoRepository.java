package repository;

import org.springframework.data.repository.CrudRepository;

import com.prova.web.model.Gioco;

public interface GiocoRepository extends CrudRepository<Gioco, String> {

}
