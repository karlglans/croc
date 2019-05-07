package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
//	Users findDistinctById(int id);

	List<Users> findUsersByGroupId(int id);
}
