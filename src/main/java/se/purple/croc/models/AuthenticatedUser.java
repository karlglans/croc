package se.purple.croc.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import se.purple.croc.domain.Role;
import se.purple.croc.domain.Users;

import java.util.*;

@Data
public class AuthenticatedUser implements OAuth2User, UserDetails {
	private String username;
	private String password;
	private String sub;
	private String email;
	private String provider;
	private int userId;
	private boolean enabled = true;
	private Set<GrantedAuthority> authorities = new HashSet<>();
	private Map<String, Object> attributes;

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
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

	public static AuthenticatedUser create(Users user) {
//		List<GrantedAuthority> authorities = Collections.
//				singletonList(new SimpleGrantedAuthority("ROLE_USER"));


		AuthenticatedUser authenticatedUser = new AuthenticatedUser();
		authenticatedUser.setUserId(user.getId());
		authenticatedUser.setEmail(user.getEmail());
		authenticatedUser.setUsername(String.valueOf(user.getId()));
		authenticatedUser.getAuthorities().add(new SimpleGrantedAuthority("ROLE_USER"));
		return authenticatedUser;

//		return new AuthenticatedUser(
//				user.getId(),
//				user.getEmail(),
//				"ssss",
//				authorities
//		);
	}

	public static AuthenticatedUser create(Users user, Map<String, Object> attributes) {
		AuthenticatedUser userPrincipal = AuthenticatedUser.create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	@Override
	public String getName() {
		return String.valueOf(userId);
	}
}
