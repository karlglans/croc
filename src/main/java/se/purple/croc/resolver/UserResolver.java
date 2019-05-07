package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.UserDto;
import se.purple.croc.repository.UserRepository;

public class UserResolver implements GraphQLResolver<UserDto> {
	@Autowired
	UserRepository userRepository;

	public UserDto getUsers(UserGroup userGroup) {
		UserDto user = new UserDto();
		user.setId(1111);
		user.setEmail("banana@mail.com");
		return user;
	}
}
