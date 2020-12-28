package com.udacity.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    private JavascriptExecutor js;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    @FindBy(id = "success")
    private WebElement resultSuccess;

    public void clickContinue() {
        js.executeScript("arguments[0].click();", resultSuccess);
    }
}
