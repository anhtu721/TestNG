package demo.vn;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Testcase_02_CreateWithPlugin {
  @Test
  public void Login() {
	  System.out.println("Successfull");
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
