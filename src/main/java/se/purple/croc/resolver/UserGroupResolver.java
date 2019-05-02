package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.service.DebugInspect;
import se.purple.croc.service.UserGroupService;

import java.util.List;

@Component
public class UserGroupResolver implements GraphQLResolver<UserGroupDto> {
	@Autowired
	DebugInspect debugInspectHelper;

	@Autowired
	UserGroupService userGroupService;

	public List<UserDto> getUsers(UserGroupDto userGroup) {
		userGroupService.loadUsersInGroup(userGroup);
		return userGroup.getUsers();
	}
}
