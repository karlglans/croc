package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
	Users findDistinctById(int id);
}
