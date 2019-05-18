package se.purple.croc.graphql;

import org.springframework.stereotype.Service;
import se.purple.croc.domain.Role;
import se.purple.croc.domain.Roles;
import se.purple.croc.models.AuthenticatedUser;

import javax.annotation.PostConstruct;

@Service
public class TestDataGrabber {

	AuthenticatedUser user4 = new AuthenticatedUser();
	AuthenticatedUser user3supervisor = new AuthenticatedUser();

	@PostConstruct
	public void setup() {
		// should be identical to test-set in db
		user3supervisor.setUserId(3);
		Roles userRoleSupervisor = new Roles();
		userRoleSupervisor.setId(2);
		userRoleSupervisor.setRole(Role.supervisor);
		user3supervisor.getAuthorities().add(userRoleSupervisor);

		user4.setUserId(4);
		Roles userRole = new Roles();
		userRole.setId(1);
		userRole.setRole(Role.user);
		user4.getAuthorities().add(userRole);
	}
}
