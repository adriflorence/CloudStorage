package com.udacity.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	/**
	 * 	Write a test that signs up a new user, logs in,
	 * 	verifies that the home page is accessible,
	 * 	logs out, and verifies that the home page is no longer accessible.
	 */
	@Test
	public void newUserSignupAndLogin() {
		signUp();
		login();

		// check if Home page available
		Assertions.assertEquals("Home", driver.getTitle());
	}

	/**
	 * navigate to signup page, type in credentials and submit
	 */
	public void signUp() {
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.setFirstName("a");
		signupPage.setLastName("a");
		signupPage.setUserName("a");
		signupPage.setPassword("a");
		signupPage.signUp();
	}

	/**
	 * navigate to login page, type in credentials and submit
	 */
	protected HomePage login() {
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName("a");
		loginPage.setPassword("a");
		loginPage.login();

		return new HomePage(driver);
	}

	/**
	 * Source: https://www.guru99.com/xpath-selenium.html
	 */
	@Test
	public void testNote() {
		HomePage homePage = login();

		// test create new note
		homePage.createNote("Note Title", "Note Description", homePage);

		List<WebElement> note = homePage.getMostRecentNote();
		String NoteTitle = note.get(1).getAttribute("innerHTML");
		String NoteDescription = note.get(2).getAttribute("innerHTML");

		Assertions.assertEquals("Note Title", NoteTitle);
		Assertions.assertEquals("Note Description", NoteDescription);

		// test edit note
		homePage.editNote("Brand New Note Title", "A completely different description");

		// test delete note
		int numberOfNotes = homePage.getNumberOfNotes();
		homePage.deleteMostRecentNote();
		int newNumberOfNotes = homePage.getNumberOfNotes(); // after deletion
		Assertions.assertEquals(numberOfNotes - 1, newNumberOfNotes);
	}

	@Test
	public void createCredentials() {
		login();

		WebDriverWait wait = new WebDriverWait(driver, 3);

		// navigate to new credential
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("add-new-credential")))).click();

		// save new credential
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url")))).sendKeys("www.example.com");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-username")))).sendKeys("admin");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-password")))).sendKeys("password1234");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("save-credential")))).click();
	}

	@Test
	public void deleteCredentials() {
		login();

		WebDriverWait wait = new WebDriverWait(driver, 3);

		// navigate to new credential
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab")))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("add-new-credential")))).click();

		// save new credential
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url")))).sendKeys("www.example.com");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-username")))).sendKeys("admin");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-password")))).sendKeys("password1234");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("save-credential")))).click();


	}
}
