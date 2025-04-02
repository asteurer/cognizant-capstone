package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

public class YouTubeVideoPage extends BasePage{
    //Locator for 'Newest First' Drop Down Button
    private By commentNewestFirstButtonLocator = By.xpath("//*[@id='menu']/a[2]");
    //Locator for Comment Section Drop Down
    private By commentSectionDropDownLocator = By.xpath("//*[@id='sort-menu']/yt-sort-filter-sub-menu-renderer/yt-dropdown-menu/tp-yt-paper-menu-button");
    //Locator for Share Button close
    private By shareButtonCloseLocator = By.xpath("//*[@id='close-panel-icon']/span/div");
    //Locator for embed button
    private By embedButtonLocator = By.xpath("//*[@id='target']/yt-icon/span/div");
    //Locator for share button
    private By shareButtonLocator = By.xpath("//*[@id='top-level-buttons-computed']/yt-button-view-model/button-view-model/button/yt-touch-feedback-shape/div/div[2]");
    //Locator for description dropdown
    private By descriptionDropDownLocator = By.xpath("//*[@id='expand']");
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
        WebElement date = super.waitForElement(By.xpath("//ytd-watch-info-text[@id='ytd-watch-info-text']"));

        // Parse out the upload date
        String[] arr = date.getText().split(" ");
        return String.join(" ", Arrays.copyOfRange(arr, arr.length - 3, arr.length));
    }

    public void clickExpandButton() {
        WebElement expandButton = super.waitForElement(By.xpath("(//ytd-text-inline-expander)[2]"));
        expandButton.click();
    }

    //NOTE: WAIT NEEDED AFTER CLICKING SHARE
    public void clickShare(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(shareButtonLocator).click();
    }

    public void clickEmbed(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(embedButtonLocator).click();
    }

    public void commentSectionHandler(WebDriver driver) {
        WebElement dropDown = driver.findElement(By.id("snippet"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", dropDown);
//        Select dropdown = new Select(driver.findElement(commentSectionDropDownLocator));
//        dropdown.selectByVisibleText("Newest First");
    }
    public void clickSortBy(WebDriver driver)  throws InterruptedException {
        driver.findElement(commentSectionDropDownLocator).click();
        Thread.sleep(2000);
    }
    public void clickByNewest(WebDriver driver) {
        driver.findElement(commentNewestFirstButtonLocator).click();
    }

    public void exitShare(WebDriver driver) {
        driver.findElement(shareButtonCloseLocator).click();
    }

    public String getEmbedText(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        WebElement embedText = driver.findElement(By.xpath("//div[@id='mirror']"));
        return embedText.getDomProperty("textContent");
    }

    public boolean isSignInButtonVisible() {
        try {
            //If the element is present, return true
            waitForElement(loginButtonLocator).isDisplayed();
            return true;
        } catch(TimeoutException e) {
            //If the element is not present and we timed out, return false
            return false;
        }
    }

    public boolean isAccountMenuVisible() {
        try {
            //If the element is present, return true
            waitForElement(loggedInSettingsMenuLocator).isDisplayed();
            return true;
        } catch(TimeoutException e) {
            //If the element is not present and we timed out, return false
            return false;
        }
    }

    public void clickSignInButton() {
        waitForElement(loginButtonLocator).isDisplayed();
        driver.findElement(loginButtonLocator).click();
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
