package com.udacity.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUserName")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submitSignup")
    private WebElement submitSignupButton;

    private final JavascriptExecutor js;

    // Constructor
    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    // Setters
    public void setFirstName(String firstName) {
        js.executeScript("arguments[0].value='"+ firstName +"';", inputFirstName);
    }

    public void setLastName(String lastName) {
        js.executeScript("arguments[0].value='"+ lastName +"';", inputLastName);
    }

    public void setUserName(String userName) {
        js.executeScript("arguments[0].value='"+ userName +"';", inputUserName);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='"+ password +"';", inputPassword);
    }

    public void signUp() {
        js.executeScript("arguments[0].click();", submitSignupButton);
    }
}
