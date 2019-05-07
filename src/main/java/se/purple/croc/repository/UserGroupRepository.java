package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

	@Query("Select u from Users u JOIN FETCH u.group g where g.id = :userId")
	List<Users> getUsersByGroupId(@Param("userId") Integer groupId);
}
