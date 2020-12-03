package controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.User;
import repository.UserRepository;
import responses.BaseResponse;

public class UserControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testdeleteUSer() {
		//Necessário um usuário cadastrado
		BaseResponse response = new BaseResponse();
		UserRepository userRepository = null;
		UserController ctl = new UserController(userRepository);
		
		User u = new User();
		ctl.addUser(u);
		
		Assert.assertEquals(response, ctl.deleteUser(u.getName()));
	}

}
