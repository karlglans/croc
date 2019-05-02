package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.UserDto;
import se.purple.croc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<UserDto> getAllUSers() {
		return new ArrayList<>();
	}

	public UserDto makeUserDto(Users user) {
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		return userDto;
	}

	Users getUserById(final Integer userId) {
		return userRepo.findById(userId).get();
	}
}
