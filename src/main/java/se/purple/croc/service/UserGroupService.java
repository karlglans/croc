package se.purple.croc.service;

import lombok.var;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.repository.UserGroupRepository;
import se.purple.croc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

	public UserGroupDto addUserToGroup(final Integer userId, final Integer userGroupId) {
		Users user = userService.getUserById(userId);
		UserGroup userGroup = getUserGroupByIdd(userGroupId);
		userGroup.getUsers().add(user); // WARNING: will delete and add list one by one?
		return makeUserGroupDto(userGroup);
	}

	public void loadUsersInGroup(UserGroupDto userGroupDto) {
		var users = userRepo.findUsersByGroupId(userGroupDto.getId());
		List<UserDto> userDtos = new ArrayList<>();
		for (Users user : users) {
			userDtos.add(userService.makeUserDto(user));
		}
		userGroupDto.setUsers(userDtos);
	}

	UserGroupDto makeUserGroupDto(UserGroup userGroup) {
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
			userDtoList.add(userService.makeUserDto(user));
		}
		return userDtoList;
	}

	public UserGroupDto getUserGroupDtoById(int id) {
		return makeUserGroupDto(getUserGroupByIdd(id));
	}
}
