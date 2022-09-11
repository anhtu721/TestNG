package demo.vn;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testcase_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsEx;
	
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsersDriver\\chromedriver.exe");
		driver = new ChromeDriver();


		// time out cho việc tìm kiếm web element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// chỉnh size của browser lên max size
		driver.manage().window().maximize();
		
		jsEx = (JavascriptExecutor) driver;
	}
	

	//@Test
	public void TC_02_ImplicitWait() {
		driver.get("https://www.facebook.com");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@name='login']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_03_FindElement() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Case 01 - Ko tìm thấy element nào hết
		// Kết quả: Failed và quăng ra exception: No Such Element
//		driver.findElement(By.xpath("//input[@id='id_order']"));

		// Case 02 - Tìm thấy 1 element
	//	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");

		// Case 03 - Tìm thấy nhiều hơn 1 element -> thao tác với element đầu tiên
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
//	@Test
	public void TC_04_FindElements() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		List<WebElement> elements;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Case 01 - Ko tìm thấy element nào hết
//		 Kết quả: Không đánh fail testcase mà trả về 1 empty list (list rỗng)
//		elements = driver.findElements(By.xpath("//input[@id='id_order']"));
//		System.out.println("Size of list: " + elements.size());

		// Case 02 - Tìm thấy 1 element
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Size of list: " + elements.size());
		elements.get(0).sendKeys("abc@gmail.com");

		// Case 03 - Tìm thấy nhiều hơn 1 element -> thao tác với element đầu tiên
//		elements = driver.findElements(By.xpath("//button[@type='submit']"));
//		System.out.println("Size of list: " + elements.size());

	}
	
//	@Test
	public void TC_05_Explicit_Visible() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		driver.findElement(By.name("reg_email__")).sendKeys("abc@gmail.com");

		// Chờ cho element được hiển thị
		// Time out để tìm element -> implicit wait
		// Time out để chờ element ở expected conditions (status) -> explicit wait
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));
//			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
//		explicitWait.until(ExpectedConditions
//				.invisibilityOf(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"))));

	}

//	@Test
	public void TC_06_Explicit_Invisible() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
	}

//	@Test
	public void TC_06_Explicit_Presence() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");
//		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));
	}

//	@Test
	public void TC_07_Explicit_Staleness() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		WebElement regEmail = driver.findElement(By.xpath("//input[@name='reg_email__']"));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		explicitWait.until(ExpectedConditions.stalenessOf(regEmail));

	}
	
	//@Test
	public void TC_08_Loading() {
		// set Timeout cho việc tìm kiếm Web Element -> effect hàm
		// findElement/findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// set timeout cho trạng thái của Element
		explicitWait = new WebDriverWait(driver, 5);

		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		By textToday = By.id("ctl00_ContentPlaceholder1_Label1");
		Assert.assertEquals(driver.findElement(textToday).getText(), "No Selected Dates to display.");
		// kiem tra button co clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']")));
		driver.findElement(By.xpath("//a[text()='12']")).click();
	
		// Kiem tra dieu kien cua elements
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		Assert.assertEquals(driver.findElement(textToday).getText(), "Monday, September 12, 2022");

	}
	
	@Test
	public void TC_09_Iframe() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://kyna.vn");
		scrollToBottomPage();
		
		// switch vào trang Facebook ifame
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
		
		// switch trở về page trước
		driver.switchTo().defaultContent();
		
		// switch vào chat iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		
		// click vào chat iframe, bắt xpath với contains: //div[contains(@class,'button_bar')]
		driver.findElement(By.xpath("//div[@class='meshim_widget_components_chatButton_ButtonBar button_bar']")).click();
		
		// click vào nút Gửi tin nhắn
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@ng-model='login.username']/following-sibling::div[contains(@ng-show,'requiredName')]")).getText(), "Tên của bạn chưa được nhập");
		
		// switch trở về page trước
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		
		List<WebElement>courseName = driver.findElements(By.xpath("//section//div[@class='content']/h4"));
		
		Assert.assertEquals(courseName.size(), 9);
		
		for(WebElement course:courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}		
	}
	
	public void scrollToBottomPage() {
		jsEx.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

}
