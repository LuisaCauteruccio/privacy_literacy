package repository;

import org.springframework.data.repository.CrudRepository;

import com.prova.web.model.Test;

public interface TestRepository extends CrudRepository<Test, String> {

}
