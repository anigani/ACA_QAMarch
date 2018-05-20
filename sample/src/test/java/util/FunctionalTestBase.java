package util;

import org.apache.commons.lang3.RandomStringUtils;
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
	
	/**
	 * 
	 * @param length
	 * 
	 * @return random email
	 */
	public String generateEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		email = temp.substring(0, temp.length() - 8) + "@mail.ru";
		return email;
	}
	
	/**
	 * 
	 * @param length
	 * 
	 * @return random password
	 */
	public String generatePassword(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-.@#$%"; // special characters
		
		return RandomStringUtils.random(length, allowedChars);
	}
	
}
