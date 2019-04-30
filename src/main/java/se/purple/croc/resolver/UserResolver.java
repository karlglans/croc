package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.repository.UserRepository;

public class UserResolver implements GraphQLResolver<Users> {
	@Autowired
	UserRepository userRepository;

	public Users getUsers(UserGroup userGroup) {
		Users user = new Users();
		user.setId(111);
		user.setEmail("banana@mail.com");
		return user;
	}
}
