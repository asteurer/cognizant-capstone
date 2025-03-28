package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class YouTubeVideoPage extends BasePage{
    //Locator for embed text
    private By embedTextLocator = By.xpath("//*[@id='textarea']");
    //Locator for embed button
    private By embedButtonLocator = By.xpath("//*[@id='target']/yt-icon/span/div");
    //Locator for share button
    private By shareButtonLocator = By.xpath("//*[@id='top-level-buttons-computed']/yt-button-view-model/button-view-model/button/yt-touch-feedback-shape/div/div[2]");
    //Locator for description dropdown
    private By descriptionDropDownLocator = By.xpath("//*[@id='expand']");
    //Locator for date once dropdown is executed
    private By datePostedLocator = By.xpath("//*[@id='info']/span[3]");
    //Locator for sign-in button
    private By loginButtonLocator = By.xpath("//span[text()='Sign in']/../../..");
    //Locator for logged out menu
    private By loggedOutSettingsMenuLocator = By.xpath("//yt-icon[@class='style-scope ytd-topbar-menu-button-renderer']");
    //Locator for account menu
    private By loggedInSettingsMenuLocator = By.xpath("//button[@aria-label='Account menu']");
    //Locator for sign out button
    private By signOutSettingsOptionLocator = By.xpath("//yt-formatted-string[text()='Sign out']");
    //Locator for settings button
    private By settingsSettingsOptionLocator = By.xpath("//yt-formatted-string[text()='Settings']");
    //Locator for subscribe button
    private By subscribeButtonLocator = By.xpath("//*[@id='subscribe-button-shape']/button");
    //Locator for subscribe pop up when not signed in
    private By subscribePopUpTextLocator = By.xpath("//yt-formatted-string[text()='Sign in to subscribe to this channel.']");

    public YouTubeVideoPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        super.visit("https://www.youtube.com/watch?v=lC0jzd8sGIA");
    }

    public String getVidDate() {
        return driver.findElement(datePostedLocator).getText();
        //needs YouTube dropdown functionality to get exact date
    }

    public void clickExpandButton(WebDriver driver) {
        driver.findElement(descriptionDropDownLocator).click();
    }

    //NOTE: WAIT NEEDED AFTER CLICKING SHARE
    public void clickShare(WebDriver driver) {
        driver.findElement(shareButtonLocator).click();
    }

    public void clickEmbed(WebDriver driver) {
        driver.findElement(embedButtonLocator).click();
    }

    public boolean isSignInButtonVisible() {
        return waitForElement(loginButtonLocator).isDisplayed();
    }

    /**
     * Clicks the settings menu. If the user is logged in, uses a separate locator
     */
    public void clickSettingsMenu() {
        if(driver.findElement(loginButtonLocator).isDisplayed()) {
            driver.findElement(loggedOutSettingsMenuLocator).click();
        } else {
            driver.findElement(loggedInSettingsMenuLocator).click();
        }
    }

    public void clickSignOut() {
        driver.findElement(signOutSettingsOptionLocator).click();
    }

    public void clickSubscribeButton() {
        driver.findElement(subscribeButtonLocator).click();
    }

    public boolean isSubscriptionPopUpVisible() {
        return driver.findElement(subscribePopUpTextLocator).isDisplayed();
    }

    public String getSubscriptionPopUpText() {
        return driver.findElement(subscribePopUpTextLocator).getText();
    }

    public void clickSettingsButton() {
        waitForElement(settingsSettingsOptionLocator).click();
    }
}
