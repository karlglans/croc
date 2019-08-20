package se.purple.croc.graphql;

import org.springframework.stereotype.Service;
import se.purple.croc.domain.Role;
import se.purple.croc.models.AuthorityRole;
import se.purple.croc.security.UserPrincipal;

import javax.annotation.PostConstruct;

@Service
public class TestDataGrabber {

	UserPrincipal user4 = new UserPrincipal();
	UserPrincipal user3supervisor = new UserPrincipal();


	@PostConstruct
	public void setup() {
		// should be identical to test-set in db
		user3supervisor.setUserId(3);
		AuthorityRole userRoleSupervisor = new AuthorityRole();
		userRoleSupervisor.setRole(Role.supervisor);
		user3supervisor.setRole(Role.supervisor);
		user3supervisor.getAuthorities().add(userRoleSupervisor);

		user4.setUserId(4);
		AuthorityRole userRole = new AuthorityRole();
		userRole.setRole(Role.user);
		user4.setRole(Role.user);
		user4.getAuthorities().add(userRole);
	}
}
