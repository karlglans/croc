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
import org.springframework.stereotype.Service;
import se.purple.croc.security.AuthHelper;
import se.purple.croc.security.UserPrincipal;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

@Service
public class TokenAuthenticationService {

	@Value("${security.token.secret}")
	String secret;

	@Value("${security.token.issuer}")
	String issuer;

	@Value("${security.token.users.expireTime}")
	Long usersExpireTime; // milliSeconds until token expire

	@Autowired
	AuthHelper authHelper;


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
	}

	private Date makeExpireDate() {
		Date date = new Date();
		date.setTime(date.getTime() + usersExpireTime);
		return date;
	}

	private static int extractIdFromSubject(String subject) {
		// NOTE: for now sub is just user id. In the future it might be a hash or Guid
		return Integer.parseInt(subject);
	}

	public UserPrincipal extractUserFromToken(Object token) {
		String strToken = token.toString();
		DecodedJWT jwt;
		try {
			jwt = verifier.verify(strToken);
		} catch (JWTVerificationException exception) {
			throw new BadCredentialsException("Missing Authentication Token");
		}
		Map<String, Claim> claims = jwt.getClaims(); // Key is the Claim name

		UserPrincipal authUser = new UserPrincipal();
		authUser.setUsername(claims.get("email").asString());
		authUser.setEmail(claims.get("email").asString());
		authHelper.addRoleFromString(authUser, claims.get("roles").asString());
		authUser.setSub(jwt.getSubject());
		authUser.setProvider("google"); // only option for now;
		authUser.setUserId(extractIdFromSubject(jwt.getSubject()));
		System.out.println(String.format("success auth by user sub: %d", authUser.getUserId())); // remove

		return authUser;
	}

	public String createToken(UserPrincipal userPrincipal) {
		return JWT.create()
				.withIssuer(issuer)
				.withClaim("email", userPrincipal.getEmail())
				.withClaim("roles", authHelper.getRoleNameFromAuthorities(userPrincipal))
				.withClaim("exp", makeExpireDate())
				.withSubject(userPrincipal.getSub())
				.sign(algorithm);
	}

}
