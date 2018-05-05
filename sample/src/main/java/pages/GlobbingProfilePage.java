package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.PageObject;

public class GlobbingProfilePage extends PageObject {

	public GlobbingProfilePage(WebDriver driver) {
		super(driver);
	}
	
	public static final String profileNameXpath = "//a[@class='user--name f_userName']";

	@FindBy(xpath = profileNameXpath)
	public WebElement profileName;
	
	@FindBy(xpath = "//nav//a[contains(@href,'logout')]")
	WebElement signoutElement;
	
	public boolean isOK() {
		waitForLoad();
		return getURL().contains("profile");		
	}
	
	public String getProfileName() {	
		return profileName.getText();
	}
	
	public GlobbingHomePage signout() {
		
		signoutElement.click();
		
		return new GlobbingHomePage(driver);
	}

}
