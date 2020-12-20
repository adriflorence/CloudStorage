package com.udacity.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submitLogin")
    private WebElement submitLoginButton;

    private final JavascriptExecutor js;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    // Setters
    public void setUserName(String userName) {
        js.executeScript("arguments[0].value='"+ userName +"';", inputUserName);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='"+ password +"';", inputPassword);
    }

    public void login() {
        js.executeScript("arguments[0].click();", submitLoginButton);
    }
}
