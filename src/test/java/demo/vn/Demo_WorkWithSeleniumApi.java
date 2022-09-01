package demo.vn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo_WorkWithSeleniumApi {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsEx;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsersDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		// ep kieu tuong minh
		jsEx = (JavascriptExecutor) jsEx;

		// time out cho việc tìm kiếm web element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// chỉnh size của browser lên max size
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	//@Test
	public void test_01_webBrowser() {
		By myAccountLocator = By.xpath("//div[@class='footer-container']//a[@title='My Account']");
		By btnCreateAccount = By.xpath("//a[@title='Create an Account']");

		// vào trang web bằng url
		driver.get("http://live.techpanda.org/");

		// click vào My Account ở footer
		driver.findElement(myAccountLocator).click();

		// Kiểm tra URL, Title
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		// click vào Create an account button
		driver.findElement(btnCreateAccount).click();

		// Kiểm tra URL, Title
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	//@Test
	public void test_02_textbox() {
		// truy cap vao trang web
		driver.get("http://live.techpanda.org/");
		// tim textbox va nhap du lieu
		driver.findElement(By.id("search")).sendKeys("Maps");
		sleepInSeconds(2);
		// xoa du lieu trn textbox
		driver.findElement(By.id("search")).clear();
		sleepInSeconds(2);
		// Kiemtra gia tri textbox
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("maxlength"), "128");

	}

	//@Test
	public void test_03_btnLogin() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		// verify button is Disable
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
		
		// input email, pass
		driver.findElement(By.id("login_username")).sendKeys("0938447156");
		driver.findElement(By.id("login_password")).sendKeys("123456789");
		
		
		
		// verify button is Enable
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		sleepInSeconds(2);
	}
	
	//@Test
	public void test_04_dropdownList() {
		// truy cap vao trang web
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		//Tao new select chua element va gan vao gia tri selectDate
		Select selectDate = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		// kieu tra element
		Assert.assertEquals(selectDate.getOptions().size(), 32);
		sleepInSeconds(3);
		// chon gia tri elemen 
		selectDate.selectByValue("23");
		sleepInSeconds(3);
		
		Assert.assertEquals("23", selectDate.getFirstSelectedOption().getText());
		sleepInSeconds(2);

	}
	
	@Test
	public void test_05_checkbox() {
		//Truy cap vao trang web
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		// Tim text box va click
		driver.findElement(By.xpath("//input[@id='Newsletter']")).click();
		sleepInSeconds(3);
	}
	
	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void removeAttributeByJSE(By by) {
		WebElement element = driver.findElement(by);
		jsEx.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
}
