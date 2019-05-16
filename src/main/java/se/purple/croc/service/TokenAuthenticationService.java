package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Users;
import se.purple.croc.models.AuthenticatedUser;

@Service
public class TokenAuthenticationService {

	@Autowired
	AuthService authService;

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
			// later on, put this back:
			// throw new BadCredentialsException("Missing Authentication Token");
		}
		int userId = Integer.parseInt(strToken);
		Users user = authService.getUser(userId);

		// TODO: check token.
		System.out.println(String.format("success auth by user: %d", userId)); // remove
		AuthenticatedUser authUser = new AuthenticatedUser();
		authUser.setEmail(user.getEmail());
		authUser.getAuthorities().addAll(user.getRoles());
		authUser.setUsername("User " + token);
		authUser.setUserId(Integer.parseInt(strToken));

		return authUser;
	}

}
