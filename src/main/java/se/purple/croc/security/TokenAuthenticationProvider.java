package se.purple.croc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import se.purple.croc.service.TokenAuthenticationService;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	@Autowired
	TokenAuthenticationService tokenAuth;

	@Override
	protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
		// Nothing to do
	}

	@Override
	protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
		final Object token = authentication.getCredentials();
		return tokenAuth.extractUserFromToken(token);
	}
}
