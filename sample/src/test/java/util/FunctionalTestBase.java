package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class FunctionalTestBase {
	protected WebDriver driver;
	String chromeDriverProp = "webdriver.chrome.driver";
	String driverPath = "D:\\ProgramFiles\\chromedriver.exe";

	protected final String URL = "https://globbing.com";
	protected final String EMAIL = "ani.danielyan.81@mail.ru";
	protected final String PASSWORD = "acatest2018";
	

	@BeforeClass
	public void setup() {
		System.setProperty(chromeDriverProp, driverPath);
		driver = new ChromeDriver();
		driver.get(URL);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
