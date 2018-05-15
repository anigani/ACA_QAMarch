package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.PageObject;

public class FacebookHomePage extends PageObject {
	public static final String alertContentXpath = "//i[contains(@class,'fb_logo')]";

	public FacebookHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//tbody//td/input[@name='email']")
	WebElement mailElement;

	@FindBy(xpath = "//input[@name='pass']")
	WebElement passwordElement;

	@FindBy(xpath = "//input[@value='Log In']")
	WebElement submitElement;

	public boolean isOK() {
		waitForLoad();
		return getURL().contains("https://web.facebook.com");
	}

	public FacebookProfilePage login(String mail, String password) {
		setInput(mailElement, mail);

		setInput(passwordElement, password);

		submitElement.click();

		return new FacebookProfilePage(driver);
	}

	private void setInput(WebElement element, String value) {
		if (element != null && element.isDisplayed()) {
			// element.click();
			element.sendKeys(value);
		}
	}
}
