package facebook;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

		Assert.assertNotNull(mailElement);
		Assert.assertNotNull(passElement);
		Assert.assertNotNull(submitElement);
		Assert.assertNotNull(forgotAccountElement);
	}

	@Test
	public void loginTestOK() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);

		Assert.assertTrue(facebookProfilePage.isOK());
		Assert.assertTrue(facebookProfilePage.isElementPresent(FacebookProfilePage.profileNameXpath));
		Assert.assertEquals(facebookProfilePage.getProfileName(), "Aniko");

	}

	@Test
	public void wrongPasswordKO() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		facebookHomePage.login(EMAIL, "test2018");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		Assert.assertTrue(facebookLoginPage.isOK());
		Assert.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.passwordAlerXpath));

	}

	@Test
	public void wrongEmailKO() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		facebookHomePage.login("asklm@m", PASSWORD);

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		Assert.assertTrue(facebookLoginPage.isOK());
		Assert.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.emailAlertXpath));

	}

	@Test
	public void emptyPasswordFieldsKO() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);
		facebookHomePage.login(EMAIL, "");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		Assert.assertTrue(facebookLoginPage.isOK());
		Assert.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.passwordAlerXpath));
	}

	@Test
	public void emptyEmaildFields() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		facebookHomePage.login("", PASSWORD);

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		Assert.assertTrue(facebookLoginPage.isOK());
		Assert.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.emailAlertXpath));

	}

	@Test
	public void EmptyFieldsKO() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);
		facebookHomePage.login("", "");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		Assert.assertTrue(facebookLoginPage.isOK());
		Assert.assertTrue(facebookLoginPage.isElementPresent(FacebookLoginPage.emailAlertXpath));
	}

	@Test
	public void signoutTest() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);

		FacebookHomePage facebookHomePage2 = facebookProfilePage.signout();

		Assert.assertTrue(facebookHomePage2.isOK());
	}

}
