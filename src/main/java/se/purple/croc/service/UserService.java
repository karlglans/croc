package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Role;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.security.UserPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
			usersDtoList.add(UserDto.makeUserDto(user));
		}
		return usersDtoList;
	}

	public UserDto getUser(final Integer userId) {
		return UserDto.makeUserDto(getUserById(userId));
	}

	public Set<UserGroupDto> getUserGroupDtoByUserId(final Integer userId) {
		Users user = getUserById(userId);
		Set<UserGroup> userGroupList = user.getGroup();
		return userGroupList.stream()
				.map(UserGroupService::makeUserGroupDto)
				.collect(Collectors.toSet());
	}

	public Users getUserById(final Integer userId) {
		return userRepo.findById(userId).get();
	}

	public Users registerNewUser(String email) {
		Users user = new Users();
		user.setEmail(email);
		user.setRole(Role.pending);
		return userRepo.save(user);
	}

	public void loadUser(UserPrincipal userPrincipal) {
		Optional<Users> optUser = userRepo.findById(userPrincipal.getUserId());
		if (optUser.isPresent()) {
			Users user = optUser.get();
//			userPrincipal.setRole(user.getRole());
		}
	}


	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
	public void acceptUser(final Integer userId) {
		Users user = getUserById(userId);
		if (user.getRole() == Role.pending) {
			user.setRole(Role.user);
			userRepo.save(user);
		}
	}
}
