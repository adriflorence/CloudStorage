package com.udacity.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    private final JavascriptExecutor js;
    private final WebDriverWait wait;
    private final WebDriver driver;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
        this.driver = driver;
    }

    // ------ NOTES -------

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "add-new-note")
    private WebElement addNewNote;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note")
    private WebElement saveNoteButton;

    // ------ CREDENTIALS -------

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "add-new-credential")
    private WebElement addNewCredential;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUserName;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "save-credential")
    private WebElement saveCredentialButton;

    // ------ NOTE METHODS -------

    public void navigateToNotesTab() {
        js.executeScript("arguments[0].click();", navNotesTab);
    }

    public void addNewNote() {
        js.executeScript("arguments[0].click();", addNewNote);
    }

    public void setNoteTitle(String freeTextNoteTitle) {
        js.executeScript("arguments[0].value='" + freeTextNoteTitle + "';", noteTitle);
    }

    public void setNoteDescription(String freeTextNoteDescription) {
        js.executeScript("arguments[0].value='"+ freeTextNoteDescription +"';", noteDescription);
    }

    public void saveNote() {
        js.executeScript("arguments[0].click();", saveNoteButton);
    }

    public void createNote(String noteTitle, String noteDescription) {
        navigateToNotesTab();
        addNewNote();
        setNoteTitle(noteTitle);
        setNoteDescription(noteDescription);
        saveNote();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickContinue();
        navigateToNotesTab();
    }

    public List<WebElement> getMostRecentRowFromTable(String tableName) {
        WebElement table = driver.findElement(By.id(tableName));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        WebElement lastRow = tableRows.get(tableRows.size() - 1);
        List<WebElement> dataCells = lastRow.findElements(By.tagName("td"));
        return dataCells;
    }

    public void deleteMostRecentNote() {
        List<WebElement> dataCells = getMostRecentRowFromTable("note-table");
        WebElement actionButtons = dataCells.get(0);
        WebElement deleteNoteButton = actionButtons.findElement(By.id("delete-note"));
        js.executeScript("arguments[0].click();", deleteNoteButton);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickContinue();
    }

    public int getNumberOfRows(String tableName) {
        WebElement noteTable = driver.findElement(By.id(tableName));
        List<WebElement> tableRows = noteTable.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public void editMostRecentNote(String noteTitle, String noteDescription) {
        List<WebElement> dataCells = getMostRecentRowFromTable("note-table");
        WebElement actionButtons = dataCells.get(0);
        WebElement editNoteButton = actionButtons.findElement(By.id("edit-note"));
        js.executeScript("arguments[0].click();", editNoteButton);
        setNoteTitle(noteTitle);
        setNoteDescription(noteDescription);
    }

    // ------ CREDENTIAL METHODS -------

    public void navigateToCredentialsTab(){
        js.executeScript("arguments[0].click();", navCredentialsTab);
    }

    public void addNewCredential() {
        js.executeScript("arguments[0].click();", addNewCredential);
    }

    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", credentialUrl);
    }

    public void setCredentialUserName(String userName) {
        js.executeScript("arguments[0].value='"+ userName +"';", credentialUserName);
    }

    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='"+ password +"';", credentialPassword);
    }

    public void saveCredential() {
        js.executeScript("arguments[0].click();", saveCredentialButton);
    }

    public void createCredential(String url, String userName, String password) {
        navigateToCredentialsTab();
        addNewCredential();
        setCredentialUrl(url);
        setCredentialUserName(userName);
        setCredentialPassword(password);
        saveCredential();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickContinue();
        navigateToCredentialsTab();
    }

    public void editMostRecentCredential(String url, String userName, String password) {
        List<WebElement> dataCells = getMostRecentRowFromTable("credential-table");
        WebElement actionButtons = dataCells.get(0);
        WebElement editCredentialButton = actionButtons.findElement(By.id("edit-credential"));
        js.executeScript("arguments[0].click();", editCredentialButton);
        setCredentialUrl(url);
        setCredentialUserName(userName);
        setCredentialPassword(password);
        saveCredential();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickContinue();
        navigateToCredentialsTab();
    }

    public void deleteMostRecentCredential() {
        List<WebElement> dataCells = getMostRecentRowFromTable("credential-table");
        WebElement actionButtons = dataCells.get(0);
        WebElement deleteCredentialButton = actionButtons.findElement(By.id("delete-credential"));
        js.executeScript("arguments[0].click();", deleteCredentialButton);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickContinue();
    }

}
