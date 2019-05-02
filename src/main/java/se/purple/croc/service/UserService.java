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

	public List<Users> getUsers() {
		return userRepo.findAll();
	}

	public List<UserDto> getAllUSers() {
		List<Users> users = getUsers();
		List<UserDto> usersDtoLsit = new ArrayList<>();
		for (Users user : users) {
			usersDtoLsit.add(makeUserDto(user));
		}
		return usersDtoLsit;
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
