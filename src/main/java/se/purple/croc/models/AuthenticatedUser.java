package se.purple.croc.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.purple.croc.domain.Role;

import java.util.HashSet;
import java.util.Set;

@Data
public class AuthenticatedUser implements UserDetails {
	private String username;
	private String password;
	private String sub;
	private String email;
	private String provider;
	private int userId;
	private boolean enabled = true;
	private Set<GrantedAuthority> authorities = new HashSet<>();

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public boolean hasRole(Role role) {
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().compareTo(role.name()) == 0) return true;
		}
		return false;
	}
}
