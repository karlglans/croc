package se.purple.croc.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
public class Roles implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Enumerated(EnumType.STRING)
	Role role;

	@Override
	public String getAuthority() {
		// NOTE: maybe spring wants the format "ROLE_" + role.name();
		return role.name();
	}
}
