package com.youtube.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YouTubeHomePage extends BasePage {

    //TODO: Test search bar locator to ensure this won't return several items
    private final By searchBarLocator = By.name("search_query");
    private final By searchButtonLocator = By.xpath("//button[@title='Search' and @class='ytSearchboxComponentSearchButton']");

    public YouTubeHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Youtube home page
     */
    public void openPage() {
        super.visit("https://www.youtube.com/");
    }

    /**
     * Searches Youtube from the main menu with the specified string
     *
     * @param toSearch
     */
    public YouTubeSearchResultsPage search(String toSearch) {
        // Wait for the page to load
        WebElement searchBar = super.waitForElement(searchBarLocator);
        //Type our string into search bar
        driver.findElement(searchBarLocator).sendKeys(toSearch);
        //Click search button
        driver.findElement(searchButtonLocator).click();

        return new YouTubeSearchResultsPage(driver);
    }
}
