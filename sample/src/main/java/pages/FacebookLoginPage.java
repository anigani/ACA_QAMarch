package pages;

import org.openqa.selenium.WebDriver;

import basePage.PageObject;

public class FacebookLoginPage extends PageObject {
	
	public static final String passwordAlerXpath = "//a[text()='Forgot Password?']";
	public static final String emailAlertXpath = "//a[text()='Sign up for an account.']";
	
	public FacebookLoginPage(WebDriver driver) {
		super(driver);
	}
	
//@FindBy(xpath = "//a[text()='Forgot Password?']")
	//public WebElement passwordAlertWindow;
	
	//@FindBy(xpath = "//a[text()='Sign up for an account.']")
	//WebElement emailAlertWindow;
	
	public boolean isOK() {
		waitForLoad();
		return getURL().contains("https://web.facebook.com/login");
	}


}
