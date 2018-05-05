package globbing;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.GlobbingHomePage;
import pages.GlobbingProfilePage;
import util.FunctionalTestBase;

public class MyTestClass extends FunctionalTestBase {
	

	@Test
	public void loginTestOK() throws InterruptedException {
		GlobbingHomePage globbingHomePage = new GlobbingHomePage(driver);
		
		GlobbingProfilePage globbingProfilePage = globbingHomePage.login(EMAIL, PASSWORD);
		
		Assert.assertTrue(globbingProfilePage.isOK());
		Assert.assertTrue(globbingProfilePage.isElementPresent(GlobbingProfilePage.profileNameXpath));
		Assert.assertEquals(globbingProfilePage.getProfileName(), "Ani");

	}
	
	@Test
	public void loginTestKO() throws InterruptedException {
		GlobbingHomePage globbingHomePage = new GlobbingHomePage(driver);
		
		GlobbingProfilePage globbingProfilePage = globbingHomePage.login(EMAIL, "test2018");
		
		Assert.assertTrue(globbingProfilePage.isElementPresent(GlobbingHomePage.alertContentXpath));
		Assert.assertEquals(globbingProfilePage.getURL(), "https://globbing.com/");

	}
	
	@Test
	public void signoutTest() throws InterruptedException {
		GlobbingHomePage globbingHomePage = new GlobbingHomePage(driver);
		
		GlobbingProfilePage globbingProfilePage = globbingHomePage.login(EMAIL, PASSWORD);
		
		GlobbingHomePage globbingHomePage2 = globbingProfilePage.signout();
		
		Assert.assertTrue(globbingHomePage2.isOK());
	}
	
}
