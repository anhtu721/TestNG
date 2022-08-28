package demo.vn;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ManageListener.class)
public class Testcase_01_CreateTestNG {

	@Test
	public void testLogin() {
		System.out.println("Login");
	}
	
	@Test
	public void testLogout() {
		System.out.println("Logout");
		Assert.assertTrue(false);
	}
	
	@Test
	public void testSignin() {
		System.out.println("Signin");
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@BeforeClass
	public void beforeClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

}
