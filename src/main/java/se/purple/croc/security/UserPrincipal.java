package se.purple.croc.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import se.purple.croc.domain.Role;
import se.purple.croc.domain.Users;

import java.util.*;

@Data
public class UserPrincipal implements OAuth2User, UserDetails {
	private String username;
	private String password;
	private String sub;
	private String email;
	private String provider;
	private int userId;
	private boolean enabled = true;
	private Set<GrantedAuthority> authorities = new HashSet<>();
	private Map<String, Object> attributes;
//	private Role role;

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

	// TODO remove since we only allow one role per user
	public boolean hasRole(Role role) {
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().compareTo(role.name()) == 0) return true;
		}
		return false;
	}

//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}

	public static UserPrincipal create(Users user, AuthHelper authHelper) {
		UserPrincipal authenticatedUser = new UserPrincipal();
		authenticatedUser.setUserId(user.getId());
		authenticatedUser.setEmail(user.getEmail());
		authenticatedUser.setUsername(String.valueOf(user.getId()));
//		authenticatedUser.setRole(user.getRole());
		authenticatedUser.setSub(String.valueOf(user.getId())); // will use user id as sub for now
		authHelper.addRoleToAuthorities(authenticatedUser, user.getRole());
		return authenticatedUser;
	}

	public static UserPrincipal create(Users user, Map<String, Object> attributes, AuthHelper authItems) {
		UserPrincipal userPrincipal = UserPrincipal.create(user, authItems);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	@Override
	public String getName() {
		return String.valueOf(userId);
	}
}
