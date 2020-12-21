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

    public void addNewNote() {
        js.executeScript("arguments[0].click();", addNewNote);
    }

    public void navigateToNotesTab() {
        js.executeScript("arguments[0].click();", navNotesTab);
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

    public void createNote(String noteTitle, String noteDescription, HomePage homePage) {
        homePage.navigateToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle(noteTitle);
        homePage.setNoteDescription(noteDescription);
        homePage.saveNote();
        homePage.navigateToNotesTab();
    }

    public List<WebElement> getMostRecentNote() {
        WebElement noteTable = driver.findElement(By.id("note-table"));
        List<WebElement> tableRows = noteTable.findElements(By.tagName("tr"));
        WebElement lastRow = tableRows.get(tableRows.size() - 1);
        List<WebElement> dataCells = lastRow.findElements(By.tagName("td"));
        return dataCells;
    }

    public void deleteMostRecentNote() {
        WebElement noteTable = driver.findElement(By.id("note-table"));
        List<WebElement> tableRows = noteTable.findElements(By.tagName("tr"));
        WebElement lastRow = tableRows.get(tableRows.size() - 1);
        List<WebElement> dataCells = lastRow.findElements(By.tagName("td"));
        WebElement actionButtons = dataCells.get(0);
        WebElement deleteNoteButton = actionButtons.findElement(By.id("delete-note"));
        js.executeScript("arguments[0].click();", deleteNoteButton);
    }

    public int getNumberOfNotes() {
        WebElement noteTable = driver.findElement(By.id("note-table"));
        List<WebElement> tableRows = noteTable.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public void editNote(String noteTitle, String noteDescription) {
        // TODO
    }

}
