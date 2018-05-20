package facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FacebookEmailConfirmPage;
import pages.FacebookRegistrationPage;
import util.FunctionalTestBase;

public class RegistrationTest extends FunctionalTestBase {

	@Test
	public void testFacebookRegPageOK() {
		String email = generateEmail(15);
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", email, email,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(facebookRegistrationPage.URL)));

		Assert.assertTrue(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_differentEmails() {
		String email = generateEmail(15);
		String email2 = generateEmail(15);
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", email, email2,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[text()='Your emails do not match. Please try again.']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_invalidEmail() {
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", "testmail.ru",
				"test@mail.ru", password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[text()='Please enter a valid mobile number or email address.']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_invalidEmail2() {
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", "test@mail.r",
				"test@mail.r", password, "Jan", "1", "1994", true);

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_invalidEmail3() {
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", "test@m.ru",
				"test@m.ru", password, "Jan", "1", "1994", true);

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_invalidEmail4() {
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", "test@mailru",
				"test@mailru", password, "Jan", "1", "1994", true);

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_emptyFirstname() {
		String email = generateEmail(15);
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("", "Smith", email, email,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='What’s your name?']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_emptyLastname() {
		String email = generateEmail(15);
		String password = generatePassword(8);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "", email, email,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='What’s your name?']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_passwordMinLength() {
		String email = generateEmail(15);
		String password = generatePassword(2);

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", email, email,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='reg_error']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_passwordOnlyAlpha() {
		String email = generateEmail(15);
		String password = "abcdefgh";

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", email, email,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='reg_error']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}

	@Test
	public void testFacebookRegPageKO_passwordOnlyNumbers() {
		String email = generateEmail(15);
		String password = "12345678";

		FacebookRegistrationPage facebookRegistrationPage = new FacebookRegistrationPage(driver);
		FacebookEmailConfirmPage facebookEmailConfirmPage = facebookRegistrationPage.register("John", "Smith", email, email,
				password, "Jan", "1", "1994", true);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='reg_error']")));

		Assert.assertFalse(facebookEmailConfirmPage.isConfirmPage());
	}
}
