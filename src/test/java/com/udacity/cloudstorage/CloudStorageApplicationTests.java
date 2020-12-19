package com.udacity.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		signUp(driver);
		login(driver);

		// check if Home page available
		Assertions.assertEquals("Home", driver.getTitle());
	}

	/**
	 * navigate to signup page, type in credentials and submit
	 */
	public void signUp(WebDriver driver) {
		driver.get("http://localhost:" + this.port + "/signup");
		driver.findElement(By.id("inputFirstName")).sendKeys("a");
		driver.findElement(By.id("inputLastName")).sendKeys("a");
		driver.findElement(By.id("inputUserName")).sendKeys("a");
		driver.findElement(By.id("inputPassword")).sendKeys("a");
		driver.findElement(By.id("signupSubmit")).click();
	}

	/**
	 * navigate to login page, type in credentials and submit
	 */
	public void login(WebDriver driver) {
		driver.get("http://localhost:" + this.port + "/login");
		driver.findElement(By.id("inputUserName")).sendKeys("a");
		driver.findElement(By.id("inputPassword")).sendKeys("a");
		driver.findElement(By.id("submitLogin")).click();
	}

	/**
	 * Source: https://www.guru99.com/xpath-selenium.html
	 */
	@Test
	public void createNote() {
		login(driver);

		WebDriverWait wait = new WebDriverWait(driver, 4);

		// navigate to new note
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-notes-tab"))));
		WebElement nextNoteTab = driver.findElement(By.id("nav-notes-tab"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextNoteTab );
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("add-new-note")))).click();

		// save new note
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-title")))).sendKeys("Note Title");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-description")))).sendKeys("Note Description");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("save-note")))).click();
	}

	@Test
	public void createCredentials() {
		login(driver);

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
		login(driver);

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
