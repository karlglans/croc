package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Form;

@Repository
public interface FromRepository extends CrudRepository<Form, Integer> {
	Form getFirstById(int Id);
}
