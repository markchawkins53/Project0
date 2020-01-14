package com.revature.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.pojo.User;

public class UserLoginServiceTest {

	private static UserLoginService uls;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		uls = new UserLoginService();
	}

	@After
	public void tearDown() throws Exception {
	}
//========================================================================
//                     Register User
//========================================================================
	/*@Test
	public void registerUserApplyUsernamePass() {
		final String username = "Le Username 123";
		
		assertEquals("Le Username 123", uls.registerUser(username, "").getUsername());
	}
	
	@Test
	public void registerUserApplyUsernameFail() {
		final String username = "Le Username 123";
		
		assertNotEquals("Le name 321", uls.registerUser(username, "").getUsername());
	}
	
	@Test
	public void registerUserApplyPasswordPass() {
		final String password = "Le Password 123";
		
		assertEquals("Le Password 123", uls.registerUser("", password).getPassword());
	}
	
	@Test
	public void registerUserApplyPasswordFail() {
		final String password = "Le Password 123";
		
		assertNotEquals("Le pass 321", uls.registerUser("", password).getPassword());
	}
	
	@Test
	public void registerUserCheckNoOwnedCars() {		
		final User dummyUser = uls.registerUser("", "");
		
		assertEquals(0, dummyUser.getOwnedCars().size());
	}
	
	@Test
	public void registerUserCheckNull () {
		uls.registerUser("Its A Me", "Mario");
		
		assertTrue(uls.registerUser("Its A Me", "Luigi") == null);
	}

//========================================================================
//               Check User Exists
//========================================================================
	
	@Test
	public void checkUserExists () {
		uls.registerUser("123", "");
		
		assertTrue(uls.checkUserExists("123"));
	}
	
	@Test
	public void checkUserDoesNotExist () {		
		assertFalse(uls.checkUserExists("Le Does Not Exist"));
	}
	
//========================================================================
//  				Authenticate User
//========================================================================
	
	@Test
	public void authenticateUserSuccess() {
		final User dummyUser2 = new User();
		
		uls.registerUser("Hi", "Ya");
		
		dummyUser2.setUsername("Hi");
		dummyUser2.setPassword("Ya");
		
		assertTrue(uls.authenticateUser(dummyUser2));
	}
	
	@Test
	public void authenticateUserFail() {
		final User dummyUser = new User();
		
		uls.registerUser("Hi", "Ya");
		
		dummyUser.setUsername("Hi");
		dummyUser.setPassword("Foo");
		
		assertFalse(uls.authenticateUser(dummyUser));
	}
	
	*/
	
}
