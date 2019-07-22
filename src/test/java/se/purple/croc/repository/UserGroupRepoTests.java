package se.purple.croc.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.domain.Users;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserGroupRepoTests {
//	@Autowired
//	UserGroupRepository userGroupRepo;
//
//	@Test
//	public void getGroupsByUserId() {
//		List<UserGroup> users = userGroupRepo.getGroupsByUserId(13);
//		assertEquals(2, users.size());
//	}
//
//
//}