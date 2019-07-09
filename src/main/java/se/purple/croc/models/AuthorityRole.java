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
		// NOTE: maybe spring wants the format "ROLE_" + role.name();
		return role.name();
	}
}
