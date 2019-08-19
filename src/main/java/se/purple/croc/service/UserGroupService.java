package se.purple.croc.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.repository.UserGroupRepository;
import se.purple.croc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserGroupService {

	@Autowired
	UserGroupRepository userGroupRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;

	public UserGroupDto createUserGroup(String name) {
		UserGroup userGroup = new UserGroup();
		userGroup.setName(name);
		userGroupRepo.save(userGroup);
		UserGroupDto userGroupDto = new UserGroupDto();
		userGroupDto.setId(userGroup.getId());
		userGroupDto.setName(userGroup.getName());
		userGroupDto.setUsers(new ArrayList<>());
		return userGroupDto;
	}

	UserGroup getUserGroupByIdd(int id) {
		return userGroupRepo.findById(id).get();
	}

	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
	public void addUserToGroup(final Integer userId, final Integer userGroupId) {
		Users user = userService.getUserById(userId);
		UserGroup userGroup = getUserGroupByIdd(userGroupId);
		user.getGroup().add(userGroup);
		// WARNING: jpa will delete and add items one by one?
	}

	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
	public void removeUserFromGroup(final Integer userId, final Integer userGroupId) {
		Users user = userService.getUserById(userId);
		UserGroup userGroup = getUserGroupByIdd(userGroupId);
		user.getGroup().remove(userGroup);
		// WARNING: jpa will delete and add items one by one?
	}


	public void loadUsersInGroup(UserGroupDto userGroupDto) {
		UserGroup ug = getUserGroupByIdd(userGroupDto.getId());
		Set<Users> groupUsers = ug.getUsers();
		List<UserDto> userDtos = new ArrayList<>();
		for (Users user : groupUsers) {
			userDtos.add(UserDto.makeUserDto(user));
		}
		userGroupDto.setUsers(userDtos);
	}

	static UserGroupDto makeUserGroupDto(UserGroup userGroup) {
		UserGroupDto userGroupDto = new UserGroupDto();
		userGroupDto.setName(userGroup.getName());
		userGroupDto.setId(userGroup.getId());
		return userGroupDto;
	}

	public List<UserGroupDto> getAllUserGroups() {
		List<UserGroupDto> userGroupsDtos = new ArrayList<>();
		for (UserGroup userGroup : userGroupRepo.findAll()) {
			userGroupsDtos.add(makeUserGroupDto(userGroup));
		}
		return userGroupsDtos;
	}

	public List<UserDto> getUsersByGroupId(int id){
		var users = userRepo.findUsersByGroupId(id);
		List<UserDto> userDtoList = new ArrayList<>();
		for (Users user : users) {
			userDtoList.add(UserDto.makeUserDto(user));
		}
		return userDtoList;
	}

	public UserGroupDto getUserGroupDtoById(int id) {
		return makeUserGroupDto(getUserGroupByIdd(id));
	}
}
