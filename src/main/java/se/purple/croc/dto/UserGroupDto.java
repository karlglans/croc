package se.purple.croc.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserGroupDto {
	private Integer id;
	private String name;
	private List<UserDto> users;
}
