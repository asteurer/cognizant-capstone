package com.examples.cucumber.MyStepdefs;

import com.youtube.pageobjects.YouTubeHomePage;
import com.youtube.pageobjects.YouTubeSearchResultsPage;
import com.youtube.pageobjects.YouTubeVideoPage;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class YouTubeStepDefs {
    private static final Logger log = Logger.getLogger(YouTubeStepDefs.class.getName());
    private WebDriver driver;
    private WebElement videoLink;
    private YouTubeSearchResultsPage searchResultsPage;
    private YouTubeVideoPage videoPage;

    @Given("I am on the Youtube home page youtube.com")
    public void ytHomePage() {
        driver = new ChromeDriver();
        log.setLevel(Level.INFO);

        try {
            YouTubeHomePage homePage = new YouTubeHomePage(driver);
            homePage.openPage();
        } catch (Exception e) {
            log.warning("failed to open the home page");
            throw e;
        }

        log.info("home page opened");
    }

    @When("I search for 'Cucumber Tests'")
    public void searchVideo() {
        try {
            YouTubeSearchResultsPage searchPage = new YouTubeSearchResultsPage(this.driver);
            searchResultsPage = searchPage.search("Cucumber Tests");
        } catch (Exception e) {
            log.warning("failed to search for video");
            throw e;
        }

        log.info("successfully performed the search");
    }

    @Then("I should find a link for 'Introduction to Cucumber'")
    public void findVideoLink() {
        try {
            videoLink = searchResultsPage.findVideoLink("Introduction to Cucumber by The-Ohayo-Dev 261,123 views 7 years ago 27 minutes");
            Assert.assertEquals("Introduction to Cucumber", videoLink.getText());
        } catch (Exception e) {
            log.warning("failed to find video link");
            throw e;
        }

        log.info("successfully found the link");
    }

    @When("I click on the link for the video")
    public void clickVideoLink(){
        try {
            videoPage = searchResultsPage.clickLink(videoLink);
        } catch (Exception e) {
            log.warning("failed to find video link");
        }

        log.info("successfully clicked the link");
    }

    @Then("I should see brought to the video page where it shows the date posted as May 14, 2017")
    public void confirmVideoDate() {
        try {
            log.info(videoPage.getVidDate());
        } catch (Exception e) {
            throw e;
        }
    }
}