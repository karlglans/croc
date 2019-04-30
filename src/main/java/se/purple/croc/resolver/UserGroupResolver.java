package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.repository.UserGroupRepository;
import se.purple.croc.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserGroupResolver implements GraphQLResolver<UserGroup> {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserGroupRepository userGroupRepository;

	@Transactional
	public Iterable<Users> getUsers(UserGroup userGroup) {
		Optional<UserGroup> group = userGroupRepository.findById(userGroup.getId());
		Iterable<Users> users = new ArrayList();
		if (!group.isPresent()) {
			return users;
		}
		Iterable<Users> usersFound = group.get().getUsers();
		return usersFound;
	}
}
