package com.udacity.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
	@Order(1)
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	@Order(2)
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * 	Write a test that signs up a new user, logs in,
	 * 	verifies that the home page is accessible,
	 * 	logs out, and verifies that the home page is no longer accessible.
	 */
	@Test
	@Order(3)
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
	@Order(4)
	public void testNotes() {
		HomePage homePage = login();

		// --- test create new note
		homePage.createNote("Note Title", "Note Description");

		List<WebElement> note = homePage.getMostRecentRowFromTable("note-table");
		String NoteTitle = note.get(1).getAttribute("innerHTML");
		String NoteDescription = note.get(2).getAttribute("innerHTML");

		Assertions.assertEquals("Note Title", NoteTitle);
		Assertions.assertEquals("Note Description", NoteDescription);

		// --- test edit note
		homePage.editMostRecentNote("Brand New Note Title", "A completely different description");

		List<WebElement> newNote = homePage.getMostRecentRowFromTable("note-table");
		String NewNoteTitle = newNote.get(1).getAttribute("innerHTML");
		String NewNoteDescription = newNote.get(2).getAttribute("innerHTML");

		Assertions.assertEquals("Note Title", NewNoteTitle);
		Assertions.assertEquals("Note Description", NewNoteDescription);

		// --- test delete note
		int numberOfNotes = homePage.getNumberOfRows("note-table");
		homePage.deleteMostRecentNote();
		int newNumberOfNotes = homePage.getNumberOfRows("note-table"); // after deletion
		Assertions.assertEquals(numberOfNotes - 1, newNumberOfNotes);
	}

	@Test
	@Order(5)
	public void testCredentials() {
		HomePage homePage = login();

		// --- test create new credential
		homePage.createCredential("url", "username", "password");

		List<WebElement> credentials = homePage.getMostRecentRowFromTable("credential-table");
		String url = credentials.get(1).getAttribute("innerHTML");
		String userName = credentials.get(2).getAttribute("innerHTML");
		//	String password = credentials.get(3).getAttribute("innerHTML");

		Assertions.assertEquals("url", url);
		Assertions.assertEquals("username", userName);

		// --- test edit credential
		homePage.editMostRecentCredential("completely_new_url.co.uk", "other_username", "abc123");

		List<WebElement> newCredential = homePage.getMostRecentRowFromTable("credential-table");
		String newCredentialUrl = newCredential.get(1).getAttribute("innerHTML");
		String newCredentialUserName = newCredential.get(2).getAttribute("innerHTML");

		Assertions.assertEquals("completely_new_url.co.uk", newCredentialUrl);
		Assertions.assertEquals("other_username", newCredentialUserName);

		// --- test delete credential
		int numberOfCredentials = homePage.getNumberOfRows("credential-table");
		homePage.deleteMostRecentCredential();
		int newNumberOfCredentials = homePage.getNumberOfRows("credential-table"); // after deletion
		Assertions.assertEquals(numberOfCredentials - 1, newNumberOfCredentials);
	}
}
