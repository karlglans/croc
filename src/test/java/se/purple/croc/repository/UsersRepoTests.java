package se.purple.croc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.Users;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UsersRepoTests {
	@Autowired
	UserRepository userRepo;

	@Test
	public void canGetSetOfUsersByGroup() {
		Set<Users> users = userRepo.findUsersByGroupId(2);
		assertEquals(4, users.size());
	}


	@Test
	public void canGetUserById() {
		Optional<Users> userOptional = userRepo.findByEmail("karlglans@gmail.com");
		assertEquals(true, userOptional.isPresent());
	}


}
