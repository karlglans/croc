package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<Users> getUsers() {
		return userRepo.findAll();
	}

	public List<UserDto> getAllUSers() {
		List<Users> users = getUsers();
		List<UserDto> usersDtoList = new ArrayList<>();
		for (Users user : users) {
			usersDtoList.add(makeUserDto(user));
		}
		return usersDtoList;
	}

	public UserDto makeUserDto(Users user) {
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		return userDto;
	}

	public UserDto getUser(final Integer userId) {
		return makeUserDto(getUserById(userId));
	}

	public Set<UserGroupDto> getUserGroupDtoByUserId(final Integer userId) {
		Users user = getUserById(userId);
		List<UserGroup> userGroupList = user.getGroup();
		return userGroupList.stream()
				.map(UserGroupService::makeUserGroupDto)
				.collect(Collectors.toSet());
	}

	public Users getUserById(final Integer userId) {
		return userRepo.findById(userId).get();
	}
}
