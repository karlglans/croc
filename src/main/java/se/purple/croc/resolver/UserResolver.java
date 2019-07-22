package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserResolver implements GraphQLResolver<UserDto> {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	public Set<UserGroupDto> getGroups(UserDto userDto) {
		return userService.getUserGroupDtoByUserId(userDto.getId());
	}
}
