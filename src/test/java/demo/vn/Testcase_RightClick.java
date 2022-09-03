package demo.vn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testcase_RightClick {
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
	public void testcase_rightClick() {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement rightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClick).perform();
		driver.findElement(By.xpath("//span[text()='Edit']")).click();
		sleepInSeconds(2);
		
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text:" + alert.getText());
		alert.accept();
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
}
