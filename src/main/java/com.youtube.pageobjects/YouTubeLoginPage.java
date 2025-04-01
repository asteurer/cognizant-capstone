package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class YouTubeLoginPage extends BasePage {

    private By emailInput = By.xpath("//input[@type='email']");
    private By nextButton = By.xpath("//span[text()='Next']");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By incorrectPasswordLabel = By.xpath("//span[text()='Wrong password. Try again or click Forgot password to reset it.']");

    public YouTubeLoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        //Wait for the email input to load
        waitForElement(emailInput).isDisplayed();

        driver.findElement(emailInput).sendKeys(username);

        driver.findElement(nextButton).click();

        //Wait for the password input to load
        waitForElement(passwordInput).isDisplayed();

        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(nextButton).click();
    }

    public boolean isIncorrectPasswordLabelVisible() {
        try {
            //If the element is present, return true
            waitForElement(incorrectPasswordLabel).isDisplayed();
            return true;
        } catch(TimeoutException e) {
            //If the element is not present and we timed out, return false
            return false;
        }
    }
}
