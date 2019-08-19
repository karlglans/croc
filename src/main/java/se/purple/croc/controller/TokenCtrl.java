package se.purple.croc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.purple.croc.security.UserPrincipal;
import se.purple.croc.service.TokenAuthenticationService;
import se.purple.croc.service.UserService;

import java.security.Principal;

@RestController
public class TokenCtrl {

	@Autowired
	TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	UserService userService;

	/**
	 * Will return an updated token for an authenticated user.
	 */
	@PostMapping(value = "/token/update")
	public String updateToken(@RequestBody String something, Principal principal) {
		UserPrincipal userPrincipal = (UserPrincipal)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
		userService.loadUser(userPrincipal);
		return tokenAuthenticationService.createToken(userPrincipal);
	}
}
