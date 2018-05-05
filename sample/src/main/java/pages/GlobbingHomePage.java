package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.PageObject;

public class GlobbingHomePage extends PageObject {
	public static final String alertContentXpath = "//div[@class='alert_content']";

	public GlobbingHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[@class='signInBtn']")
	WebElement loginElement;

	@FindBy(xpath = "//div[@class='sign--in-email']// input[@class='sign--in-field' ]")
	WebElement mailElement;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordElement;

	@FindBy(xpath = "//button[@name='Submit']")
	WebElement submitElement;

	

	public boolean isOK() {
		waitForLoad();
		return getURL().equals("https://globbing.com");
	}

	public GlobbingProfilePage login(String mail, String password) {

		loginElement.click();

		setInput(mailElement, mail);

		setInput(passwordElement, password);

		submitElement.click();

		return new GlobbingProfilePage(driver);

	}

	private void setInput(WebElement element, String value) {
		if (element != null && element.isDisplayed()) {
			// element.click();
			element.sendKeys(value);
		}
	}
}
