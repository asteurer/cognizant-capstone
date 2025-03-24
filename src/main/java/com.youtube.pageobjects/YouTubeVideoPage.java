package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
