package facebook;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FacebookHomePage;
import pages.FacebookProfilePage;
import util.FunctionalTestBase;

public class LoginTest extends FunctionalTestBase {

	@Test
	public void loginTestOK() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);

		Assert.assertTrue(facebookProfilePage.isOK());
		Assert.assertTrue(facebookProfilePage.isElementPresent(FacebookProfilePage.profileNameXpath));
		Assert.assertEquals(facebookProfilePage.getProfileName(), "Aniko");

	}

	@Test
	public void loginTestKO() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, "test2018");

		Assert.assertTrue(facebookProfilePage.isElementPresent(FacebookHomePage.alertContentXpath));
		Assert.assertTrue(facebookProfilePage.getURL().contains("https://web.facebook.com/login"));
	}

	@Test
	public void signoutTest() throws InterruptedException {
		FacebookHomePage facebookHomePage = new FacebookHomePage(driver);

		FacebookProfilePage facebookProfilePage = facebookHomePage.login(EMAIL, PASSWORD);

		FacebookHomePage facebookHomePage2 = facebookProfilePage.signout();

		Assert.assertTrue(facebookHomePage2.isOK());
	}

}
