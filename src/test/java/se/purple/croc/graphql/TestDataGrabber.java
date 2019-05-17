package se.purple.croc.graphql;

import org.springframework.stereotype.Service;
import se.purple.croc.domain.Role;
import se.purple.croc.domain.Roles;
import se.purple.croc.models.AuthenticatedUser;

import javax.annotation.PostConstruct;

@Service
public class TestDataGrabber {

	AuthenticatedUser user4 = new AuthenticatedUser();

	@PostConstruct
	public void setup() {
		// should be identical to test-set in db
		user4.setUserId(4);
		Roles userRole = new Roles();
		userRole.setId(1);
		userRole.setRole(Role.user);
		user4.getAuthorities().add(userRole);
	}
}
