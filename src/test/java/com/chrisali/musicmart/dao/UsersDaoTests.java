package com.chrisali.musicmart.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrisali.musicmart.model.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsersDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		usersDao.createOrUpdate(user1);
		
		List<User> users = usersDao.getPaginatedUsers(0, 10);
		
		assertEquals("One user should be in list", 1, users.size());
		
		usersDao.createOrUpdate(user2);
		usersDao.createOrUpdate(user3);
		
		users = usersDao.getPaginatedUsers(0, 10);
		
		assertEquals("Three users should be in list", 3, users.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("User should exist in database", usersDao.exists(user1.getId()));
		
		User noExist = new User("Nobody", "No One", "nobody@test.com", "nope", false, "ROLE_USER");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("User should not exist in database", usersDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<User> users = usersDao.getPaginatedUsers(0, 10);
		assertEquals("Three users should be in list", 3, users.size());
		
		usersDao.delete(user2.getId());
		
		users = usersDao.getPaginatedUsers(0, 10);
		assertEquals("Two users should be left in list", 2, users.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		user2.setEmail("fruehs@test.de");
		
		usersDao.createOrUpdate(user2);
		
		User updatedUser = usersDao.getUser(user2.getId());
		
		assertEquals("Updated user should equal user retrieved from database", user2, updatedUser);
	}
}
