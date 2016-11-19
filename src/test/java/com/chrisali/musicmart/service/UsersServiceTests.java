package com.chrisali.musicmart.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrisali.musicmart.config.SecurityConfiguration;
import com.chrisali.musicmart.model.user.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SecurityConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		usersService.createOrUpdate(user1);
		
		List<User> users = usersService.getPaginatedUsers(0, 10);
		
		assertEquals("One user should be in list", 1, users.size());
		
		usersService.createOrUpdate(user2);
		usersService.createOrUpdate(user3);
		
		users = usersService.getPaginatedUsers(0, 10);
		
		assertEquals("Three users should be in list", 3, users.size());
		
		long totalUsers = usersService.getTotalNumberOfUsers();
		
		assertEquals("Three users in total should be in database", 3, totalUsers);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testCreateRetrieveNoAuth() {
		testCreateRetrieve();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("User should exist in database", usersService.exists(user1.getId()));
		
		User noExist = new User("Nobody", "No One", "nobody@test.com", "nope", false, "ROLE_USER");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("User should not exist in database", usersService.exists(noExist.getId()));
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testExistsNoAuth() {
		testExists();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testDelete() {
		addTestData();
		
		List<User> users = usersService.getPaginatedUsers(0, 10);
		assertEquals("Three users should be in list", 3, users.size());
		
		usersService.delete(user2.getId());
		
		users = usersService.getPaginatedUsers(0, 10);
		assertEquals("Two users should be left in list", 2, users.size());
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testDeleteNoAuth() {
		testDelete();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testUpdate() {
		addTestData();
		
		user2.setEmail("fruehs@test.de");
		
		usersService.createOrUpdate(user2);
		
		User updatedUser = usersService.getUser(user2.getId());
		
		assertEquals("Updated user should equal user retrieved from database", user2, updatedUser);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
