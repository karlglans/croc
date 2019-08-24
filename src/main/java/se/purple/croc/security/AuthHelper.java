package se.purple.croc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import se.purple.croc.domain.Role;
import se.purple.croc.models.AuthorityRole;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Keeps some AuthorityRole objects based on se.purple.croc.domain.Role
 */

@Configuration
public class AuthHelper {
	private final AuthorityRole userAuthority = new AuthorityRole();
	private final AuthorityRole supervisorAuthority = new AuthorityRole();
	private final AuthorityRole pendingAuthority = new AuthorityRole();
	private final AuthorityRole adminAuthority = new AuthorityRole();
	private final AuthorityRole noAuthority = new AuthorityRole();

	@PostConstruct
	void setup() {
		userAuthority.setRole(Role.user);
		supervisorAuthority.setRole(Role.supervisor);
		pendingAuthority.setRole(Role.pending);
		adminAuthority.setRole(Role.administrator);
	}

	public AuthorityRole getUserAuthority() {
		return userAuthority;
	}

	public AuthorityRole getSupervisorAuthority() {
		return supervisorAuthority;
	}

	public AuthorityRole getPendingAuthority() {
		return pendingAuthority;
	}

	public void addRoleFromString(UserPrincipal authUser, String roles) {
		Set<GrantedAuthority> authorities = authUser.getAuthorities();
		if (roles.contains(Role.user.name())) {
			authorities.add(userAuthority);
		} else if (roles.contains(Role.supervisor.name())) {
			authorities.add(supervisorAuthority);
		} else if (roles.contains(Role.pending.name())) {
			authorities.add(pendingAuthority);
		}
	}

	public boolean checkRole(UserPrincipal authUser, Role role) {
		Set<GrantedAuthority> authorities = authUser.getAuthorities();
		if (role == Role.user) {
			return authorities.contains(userAuthority);
		} else if (role == Role.supervisor) {
			return authorities.contains(supervisorAuthority);
		} else if (role == Role.administrator) {
			return authorities.contains(adminAuthority);
		}
		return false;
	}

	public void addRoleToAuthorities(UserPrincipal authUser, Role role) {
		addRoleFromString(authUser, role.name());
	}

	public String getRoleNameFromAuthorities(UserPrincipal authUser) {
		Set<GrantedAuthority> authorities = authUser.getAuthorities();
		if (authorities.contains(supervisorAuthority)) {
			return Role.supervisor.name();
		}
		if (authorities.contains(userAuthority)) {
			return Role.user.name();
		}
		if (authorities.contains(pendingAuthority)) {
			return Role.pending.name();
		}
		return "noRole";
	}

}
