package se.purple.croc.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Role;
import se.purple.croc.models.AuthorityRole;
import se.purple.croc.security.UserPrincipal;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class TokenAuthenticationService {

	@Value("${security.token.secret}")
	String secret;

	@Value("${security.token.issuer}")
	String issuer;

	@Value("${security.token.users.expireTime}")
	Long usersExpireTime; // milliSeconds until token expire

	private final AuthorityRole userAuthority = new AuthorityRole();
	private final AuthorityRole supervisorAuthority = new AuthorityRole();


	@Autowired
	AuthService authService;

	private Algorithm algorithm;
	private JWTVerifier verifier;

	@PostConstruct
	public void init() {
		this.algorithm = Algorithm.HMAC256(secret);
		this.verifier = JWT.require(algorithm)
				.withIssuer(issuer)
				.build(); //Reusable verifier instance

		// TODO maybe find a better spot for these objects
		userAuthority.setRole(Role.user);
		supervisorAuthority.setRole(Role.supervisor);
	}

	private Date makeExpireDate() {
		Date date = new Date();
		date.setTime(date.getTime() + usersExpireTime);
		return date;
	}

	private void addRoles(Set<GrantedAuthority> authorities, String roles) {
		if (roles.contains("USER")) {
			authorities.add(userAuthority);
		}
		if (roles.contains("SUPER")) {
			authorities.add(supervisorAuthority);
		}
	}

	public UserPrincipal extractUserFromToken(Object token) {
		String strToken = token.toString();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(strToken);
		} catch (JWTVerificationException exception) {
			throw new BadCredentialsException("Missing Authentication Token");
		}
		Map<String, Claim> claims = jwt.getClaims(); // Key is the Claim name

		UserPrincipal authUser = new UserPrincipal();
		authUser.setUsername(claims.get("email").asString());
		authUser.setEmail(claims.get("email").asString());
		addRoles(authUser.getAuthorities(), claims.get("roles").asString());
		authUser.setSub(jwt.getSubject());
		authUser.setProvider("google"); // only option for now
		authUser.setUserId(claims.get("id").asInt());
		System.out.println(String.format("success auth by user: %d", authUser.getUserId())); // remove

		return authUser;
	}

	public String createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		return JWT.create()
				.withIssuer(issuer)
				.withClaim("email", userPrincipal.getEmail())
				.withClaim("roles", "supervisor")
				.withClaim("id", 111) // should later on be replace by subject
				.withClaim("exp", makeExpireDate())
				.withSubject("2222") // unique identifier
				.sign(algorithm);
	}

}
