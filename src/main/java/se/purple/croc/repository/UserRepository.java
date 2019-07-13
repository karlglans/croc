package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Users;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
//	Users findDistinctById(int id);

	Set<Users> findUsersByGroupId(int id);
}
