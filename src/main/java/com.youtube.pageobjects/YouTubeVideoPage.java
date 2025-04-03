package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

public class YouTubeVideoPage extends BasePage{
    //Locator for sign-in button
    private final By loginButtonLocator = By.xpath("//span[text()='Sign in']/../../..");
    //Locator for account menu
    private final By loggedInSettingsMenuLocator = By.xpath("//button[@aria-label='Account menu']");
    //Locator for subscribe pop up when not signed in
    private final By subscribePopUpTextLocator = By.xpath("//yt-formatted-string[text()='Sign in to subscribe to this channel.']");

    public YouTubeVideoPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        super.visit("https://www.youtube.com/watch?v=lC0jzd8sGIA");
    }

    /**
     * Retrieves the upload date of the YouTube video
     *
     * @return String
     */
    public String getVidDate() {
        WebElement date = super.waitForElement(By.xpath("//ytd-watch-info-text[@id='ytd-watch-info-text']"));

        // Parse out the upload date
        String[] arr = date.getText().split(" ");
        return String.join(" ", Arrays.copyOfRange(arr, arr.length - 3, arr.length));
    }

    /**
     * Expands the description section under the YouTube video
     */
    public void clickExpandButton() {
        WebElement expandButton = super.waitForElement(By.xpath("//ytd-text-inline-expander[@id='description-inline-expander']"));
        expandButton.click();
    }

    //NOTE: WAIT NEEDED AFTER CLICKING SHARE
    public void clickShare(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        //Locator for share button
        By shareButtonLocator = By.xpath("//*[@id='top-level-buttons-computed']/yt-button-view-model/button-view-model/button/yt-touch-feedback-shape/div/div[2]");
        driver.findElement(shareButtonLocator).click();
    }

    public void clickEmbed(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        //Locator for embed button
        By embedButtonLocator = By.xpath("//*[@id='target']/yt-icon/span/div");
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
        //Locator for Comment Section Drop Down
        By commentSectionDropDownLocator = By.xpath("//*[@id='sort-menu']/yt-sort-filter-sub-menu-renderer/yt-dropdown-menu/tp-yt-paper-menu-button");
        driver.findElement(commentSectionDropDownLocator).click();
        Thread.sleep(2000);
    }
    public void clickByNewest(WebDriver driver) {
        //Locator for 'Newest First' Drop Down Button
        By commentNewestFirstButtonLocator = By.xpath("//*[@id='menu']/a[2]");
        driver.findElement(commentNewestFirstButtonLocator).click();
    }

    public void exitShare(WebDriver driver) {
        //Locator for Share Button close
        By shareButtonCloseLocator = By.xpath("//*[@id='close-panel-icon']/span/div");
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
            //Locator for logged out menu
            By loggedOutSettingsMenuLocator = By.xpath("//yt-icon[@class='style-scope ytd-topbar-menu-button-renderer']");
            driver.findElement(loggedOutSettingsMenuLocator).click();
        } else {
            driver.findElement(loggedInSettingsMenuLocator).click();
        }
    }

    public void clickSignOut() {
        //Locator for sign out button
        By signOutSettingsOptionLocator = By.xpath("//yt-formatted-string[text()='Sign out']");
        driver.findElement(signOutSettingsOptionLocator).click();
    }

    public void clickSubscribeButton() {
        //Locator for subscribe button
        By subscribeButtonLocator = By.xpath("//*[@id='subscribe-button-shape']/button");
        driver.findElement(subscribeButtonLocator).click();
    }

    public boolean isSubscriptionPopUpVisible() {
        return driver.findElement(subscribePopUpTextLocator).isDisplayed();
    }

    public String getSubscriptionPopUpText() {
        return driver.findElement(subscribePopUpTextLocator).getText();
    }

    public void clickSettingsButton() {
        //Locator for settings button
        By settingsSettingsOptionLocator = By.xpath("//yt-formatted-string[text()='Settings']");
        waitForElement(settingsSettingsOptionLocator).click();
    }
}
