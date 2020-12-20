package com.udacity.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
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

}
