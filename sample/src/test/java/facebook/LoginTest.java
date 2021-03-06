package facebook;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pages.FacebookHomePage;
import pages.FacebookLoginPage;
import pages.FacebookProfilePage;
import util.FunctionalTestBase;

public class LoginTest extends FunctionalTestBase {

	@Test
	public void loginPageOK() {
		WebElement mailElement = getWebElementByXpath("//tbody//td/input[@name='email']");
		WebElement passElement = getWebElementByXpath("//input[@name='pass']");
		WebElement submitElement = getWebElementByXpath("//input[@value='Log In']");
		WebElement forgotAccountElement = getWebElementByXpath("//a[text()='Forgot account?']");

		AssertJUnit.assertNotNull(mailElement);
		AssertJUnit.assertNotNull(passElement);
		AssertJUnit.assertNotNull(submitElement);
		AssertJUnit.assertNotNull(forgotAccountElement);
	}

	@Test
	public void loginTestOK() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);

		AssertJUnit.assertTrue(facebookProfilePage.isOK());
		AssertJUnit.assertTrue(facebookProfilePage.isElementPresent(FacebookProfilePage.profileNameXpath));
		AssertJUnit.assertEquals(facebookProfilePage.getProfileName(), "Aniko");

	}
	@Test
	public void backForwardTestOK()  {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);
		
		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);
		driver.navigate().back();
		driver.navigate().to("https://web.facebook.com/");
		Assert.assertTrue(facebookProfilePage.isOK());
		Assert.assertTrue(facebookProfilePage.isElementPresent(FacebookProfilePage.profileNameXpath));
		Assert.assertEquals(facebookProfilePage.getProfileName(), "Aniko");
	
	}

	
	@Test
	public void wrongPasswordKO() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		facebookHomePage.login(EMAIL, "test2018");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		AssertJUnit.assertTrue(facebookLoginPage.isOK());
		AssertJUnit.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.passwordAlerXpath));

	}

	@Test
	public void wrongEmailKO() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		facebookHomePage.login("asklm@m", PASSWORD);

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		AssertJUnit.assertTrue(facebookLoginPage.isOK());
		AssertJUnit.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.emailAlertXpath));

	}

	@Test
	public void emptyPasswordFieldKO() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);
		facebookHomePage.login(EMAIL, "");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		AssertJUnit.assertTrue(facebookLoginPage.isOK());
		AssertJUnit.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.passwordAlerXpath));
	}

	@Test
	public void emptyEmaildFields() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		facebookHomePage.login("", PASSWORD);

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		AssertJUnit.assertTrue(facebookLoginPage.isOK());
		AssertJUnit.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.emailAlertXpath));

	}

	@Test
	public void emptyFieldsKO() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);
		facebookHomePage.login("", "");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		AssertJUnit.assertTrue(facebookLoginPage.isOK());
		AssertJUnit.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.emailAlertXpath));
	}

	@Test
	public void signoutTest() {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);

		FacebookHomePage facebookHomePage2 = facebookProfilePage.signout();

		AssertJUnit.assertTrue(facebookHomePage2.isOK());
	}

}
