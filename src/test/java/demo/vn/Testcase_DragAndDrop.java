package demo.vn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testcase_DragAndDrop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsEx;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsersDriver\\chromedriver.exe");
		driver = new ChromeDriver();


		// time out cho việc tìm kiếm web element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// chỉnh size của browser lên max size
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@Test
	public void test_dragAndDrog() {
		//truy cap vao web
		driver.get("https://demo.guru99.com/test/drag_drop.html");
		// drag
		WebElement a = driver.findElement(By.xpath("//li[@id='fourth']"));
		//drop
		WebElement b = driver.findElement(By.xpath("//ol[@id='amt7']"));
		// action
		Actions Action = new Actions(driver);
		Action.dragAndDrop(a, b).build().perform();
		sleepInSeconds(2);
	}
	
	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}