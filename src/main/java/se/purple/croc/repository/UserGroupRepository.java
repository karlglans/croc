package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import se.purple.croc.domain.UserGroup;

public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {
}
