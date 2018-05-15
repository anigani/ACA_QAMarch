package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.PageObject;

public class FacebookProfilePage extends PageObject {

	public FacebookProfilePage(WebDriver driver) {
		super(driver);
	}

	public static final String profileNameXpath = "//div[@data-click]//span/span";
	public static final String logoutXpath = "//span[text()='Log Out']";

	@FindBy(xpath = profileNameXpath)
	public WebElement profileName;

	@FindBy(xpath = "//div[@id='userNavigationLabel']")
	WebElement userNavigation;

	public boolean isOK() {
		waitForLoad();
		return getURL().contains("https://web.facebook.com/");
	}

	public String getProfileName() {
		return profileName.getText();
	}

	public FacebookHomePage signout() {
		// WebElement userNavigation =
		// driver.findElement(By.xpath("//div[@id='userNavigationLabel']"));
		userNavigation.click();

		if (isElementPresent(logoutXpath)) {
			driver.findElement(By.xpath(logoutXpath)).click();
		}

		return new FacebookHomePage(driver);
	}

}
