package demo.vn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testcase_03_CreateNew {
	@DataProvider(name = "user_account")
	public String[][] UserAccountData() {
		return new String[][] { { "user1", "123456" }, { "user2", "123456" } };
	}

	@Test(dataProvider = "user_account")
	public void test03(String userName, String passWord) {
		System.out.println("Run test case: ");
		System.out.println("Show user: " + userName);
		System.out.println("Show password: " + passWord);
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
