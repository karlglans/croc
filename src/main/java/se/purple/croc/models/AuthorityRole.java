package se.purple.croc.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import se.purple.croc.domain.Role;

import javax.persistence.*;

@Data
public class AuthorityRole implements GrantedAuthority {

	@Enumerated(EnumType.STRING)
	Role role;

	@Override
	public String getAuthority() {
		if (role == Role.supervisor) {
			return "ROLE_SUPERVISOR";
		} else if (role == Role.user) {
			return "ROLE_USER";
		} else if (role == Role.administrator) {
			return "ROLE_ADMIN";
		} else if (role == Role.pending) {
			return "ROLE_PENDING";
		}
		return "ROLE_UNDEFINED";
	}
}
