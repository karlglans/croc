package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.Users;

@Data
public class UserDto {
	private int id;
	private String email;
	public void setUser(Users user) {
		setId(user.getId());
		setEmail(user.getEmail());
	}
}
