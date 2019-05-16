package se.purple.croc.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import se.purple.croc.models.AuthenticatedUser;

@Service
public class TokenAuthenticationService {

	public AuthenticatedUser TEMP_makeDefaulUser() {
		AuthenticatedUser authUser = new AuthenticatedUser();
		authUser.setUsername("default user");
		authUser.setEmail("mail1@mail.com");
		authUser.setUserId(4);
		return authUser;
	}

	public AuthenticatedUser extractUserFromToken(Object token) {
		String strToken = token.toString();
		// no token should lead to BadCredentialsException
		if (strToken.compareTo("null") == 0) {
			return TEMP_makeDefaulUser();
			// throw new BadCredentialsException("Missing Authentication Token");
		}
		// TODO: check token.
		System.out.println(String.format("success auth by user: %s", strToken)); // remove
		AuthenticatedUser authUser = new AuthenticatedUser();
		authUser.setUsername("User " + token);
		authUser.setUserId(Integer.parseInt(strToken));
		return authUser;
	}

}
