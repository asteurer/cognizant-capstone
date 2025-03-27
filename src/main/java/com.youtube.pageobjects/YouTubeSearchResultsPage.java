package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YouTubeSearchResultsPage extends BasePage {

    //TODO: Test search bar locator to ensure this won't return several items

    public YouTubeSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Searches Youtube from the main menu with the specified string
     *
     * @param toSearch
     */
    public YouTubeSearchResultsPage search(String toSearch) {
        WebElement searchBar = super.waitForElement(By.name("search_query"));
        WebElement searchButton = super.waitForElement(By.xpath("//button[@title='Search' and @class='ytSearchboxComponentSearchButton ytSearchboxComponentSearchButtonDark']"));
        //Type our string into search bar
        searchBar.sendKeys(toSearch);
        //Click search button
        searchButton.click();

        return new YouTubeSearchResultsPage(driver);
    }

    /**
     * Clicks on a video with the full title as listed below. Throws a NoSuchElementException when no matching video is
     * found.
     *
     * @param link
     * @return YouTubeVideoPage
     * @throws NoSuchElementException
     */
    public YouTubeVideoPage clickLink(WebElement link) {
        link.click();

        //TODO: Test the exception throwing of this class

        return new YouTubeVideoPage(driver);
    }

    /**
     * Finds the video link on the page based on the full title
     *
     * @param fullTitle
     * @return
     */

    public WebElement findVideoLink(String fullTitle) {
        String xpath = String.format("//a[@id='video-title' and @class='yt-simple-endpoint style-scope ytd-video-renderer' and @aria-label='%s']", fullTitle);
        By titleXpath = By.xpath(xpath);
        return super.waitForElement(titleXpath);
    }
}
