package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class FunctionalTestBase {
	protected WebDriver driver;
	String chromeDriverProp = "webdriver.chrome.driver";
	String driverPath = "D:\\ProgramFiles\\chromedriver.exe";

	protected final String URL = "https://facebook.com";
	protected final String EMAIL = "ani.danielyan.81@mail.ru";
	protected final String PASSWORD = "Ani1981";
	

	@BeforeClass
	public void setup() {
		System.setProperty(chromeDriverProp, driverPath);
	}
	
	@BeforeMethod
	public void before() {
		driver = new ChromeDriver();
		driver.get(URL);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	protected WebElement getWebElementByXpath(String xpath) {
		WebElement we;
		try {
			we = driver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			we = null;
		}
		
		return we;
	}
}
