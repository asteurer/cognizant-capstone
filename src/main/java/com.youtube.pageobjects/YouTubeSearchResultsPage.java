package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class YouTubeSearchResultsPage extends BasePage {

    //TODO: Test search bar locator to ensure this won't return several items
    private By searchBarLocator = By.name("search_query");
    private By searchButtonLocator = By.xpath("//button[@title='Search' and @class='ytSearchboxComponentSearchButton']");

    private String searchResultByTitleContainsXpathString = "//yt-formatted-string[@class='style-scope ytd-video-renderer' and contains(.,'')]";
    private String searchResultByFullTitleXpathString = "//yt-formatted-string[@class='style-scope ytd-video-renderer' and text()='";

    public YouTubeSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Searches Youtube from the main menu with the specified string
     *
     * @param toSearch
     */
    public YouTubeSearchResultsPage search(String toSearch) {
        //Type our string into search bar
        driver.findElement(searchBarLocator).sendKeys(toSearch);
        //Click search button
        driver.findElement(searchButtonLocator).click();

        return new YouTubeSearchResultsPage(driver);
    }

    /**
     * Clicks on a video with the full title as listed below. Throws a NoSuchElementException when no matching video is
     * found.
     *
     * @param fullTitle
     * @return YouTubeVideoPage
     * @throws NoSuchElementException
     */
    public YouTubeVideoPage clickVideoByFullTitle(String fullTitle) throws NoSuchElementException {
        //Click the video listed by fullTitle
        driver.findElement(By.xpath(searchResultByFullTitleXpathString + fullTitle + "')]")).click();

        //TODO: Test the exception throwing of this class

        return new YouTubeVideoPage(driver);
    }
}
