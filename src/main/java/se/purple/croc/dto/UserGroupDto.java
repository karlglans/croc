package se.purple.croc.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserGroupDto {
	private Integer id;
	private String name;
	private List<UserDto> users;

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof UserGroupDto)) {
			return false;
		}
		UserGroupDto c = (UserGroupDto) o;
		return Integer.compare(id, c.id) == 0;
	}
}
