package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class YouTubeLoginPage extends BasePage {

    private By emailInput = By.xpath("//input[@type='email']");
    private By nextButton = By.xpath("//span[text()='Next']");
    private By passwordInput = By.xpath("//input[@type='password']");

    public YouTubeLoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        //Wait for the email input to load
        waitForElement(emailInput).isDisplayed();

        driver.findElement(emailInput).sendKeys(email);

        driver.findElement(nextButton).click();
    }

    public void enterPassword(String password) {
        //Wait for the password input to load
        waitForElement(passwordInput).isDisplayed();

        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(nextButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);

        enterPassword(password);
    }

    public boolean isIncorrectPasswordLabelVisible() {
        By incorrectPasswordLabel = By.xpath("//span[text()='Wrong password. Try again or click Forgot password to reset it.']");
        try {
            //If the element is present, return true
            waitForElement(incorrectPasswordLabel).isDisplayed();
            return true;
        } catch(TimeoutException e) {
            //If the element is not present and we timed out, return false
            return false;
        }
    }

    public boolean isIncorrectEmailLabelVisible() {
        By incorrectEmailLabel = By.xpath("//div[text()='Enter a valid email or phone number']");
        try {
            //If the element is present, return true
            waitForElement(incorrectEmailLabel).isDisplayed();
            return true;
        } catch(TimeoutException e) {
            //If the element is not present and we timed out, return false
            return false;
        }
    }

    public boolean isNoAccountLabelVisible() {
        By noAccountLabel = By.xpath("//div[text()='Couldn’t find your Google Account']");
        try {
            //If the element is present, return true
            waitForElement(noAccountLabel).isDisplayed();
            return true;
        } catch(TimeoutException e) {
            //If the element is not present and we timed out, return false
            return false;
        }
    }
}