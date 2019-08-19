package se.purple.croc.dto;

import lombok.Builder;
import lombok.Data;
import se.purple.croc.domain.Role;
import se.purple.croc.domain.Users;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {
	private int id;
	private String email;
	private String role;
	public void setUser(Users user) {
		setId(user.getId());
		setEmail(user.getEmail());
		setRole(user.getRole());
	}
	public void setRole(Role role) {
		this.role = role.toString();
	}
	public static UserDto makeUserDto(Users user) {
		return UserDto.builder()
				.email(user.getEmail())
				.id(user.getId())
				.role(user.getRole().toString())
				.build();
	}
	private List<UserGroupDto> groups = new ArrayList<>();
}
