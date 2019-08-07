package se.purple.croc.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.UserGroup;

import static org.junit.Assert.assertEquals;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserGroupRepoTests {
	@Autowired
	UserGroupRepository userGroupRepo;

	@Test
	public void getGroupByUserId() {
		UserGroup userGroup = userGroupRepo.findById(2).get();
		assertEquals(2, userGroup.getId().intValue());
	}

	@Test
	public void getUsersInGroupByGroupId() {
		UserGroup userGroup = userGroupRepo.findById(2).get();
		assertEquals(4, userGroup.getUsers().size());
	}


}