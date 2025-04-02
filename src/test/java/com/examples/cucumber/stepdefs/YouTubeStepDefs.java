package com.examples.cucumber.stepdefs;

import com.youtube.pageobjects.YouTubeHomePage;
import com.youtube.pageobjects.YouTubeSearchResultsPage;
import com.youtube.pageobjects.YouTubeVideoPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class YouTubeStepDefs extends CoreStepdefs {
    private static final Logger log = Logger.getLogger(YouTubeStepDefs.class.getName());
    //private WebDriver driver;
    private WebElement videoLink;
    private YouTubeSearchResultsPage searchResultsPage;
    private YouTubeVideoPage videoPage;

    /**
     * Teardown function for our browser.
     */
    @After("not @NoTeardown")
    public void teardown() {
        driver.quit();
        logger.info("Closed browser");
    }

    @Given("I am on the Youtube home page youtube.com")
    public void ytHomePage() {
        super.setUp("Chrome");

        //driver = new ChromeDriver();
        //log.setLevel(Level.INFO);

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
            YouTubeSearchResultsPage sp = new YouTubeSearchResultsPage(driver);
            searchResultsPage = sp.search("Cucumber Tests");
        } catch (Exception e) {
            log.warning("failed to search for video");
            throw e;
        }

        log.info("successfully performed the search");
    }

    @Then("I should find a link for 'Introduction to Cucumber'")
    public void findVideoLink() {
        try {
            videoLink = searchResultsPage.findVideoLink("Introduction to Cucumber by The-Ohayo-Dev");
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
            log.info("successfully clicked link");
        } catch (Exception e) {
            log.warning("failed to click video link");
            throw e;
        }

        log.info("successfully clicked the link");
    }

    @Then("I should see brought to the video page where it shows the date posted as May 14, 2017")
    public void confirmVideoDate() {
        try {
            videoPage.clickExpandButton();
            Assert.assertEquals("May 14, 2017", videoPage.getVidDate());
        } catch (Exception e) {
            log.warning("failed to confirm the upload date");
            throw e;
        }

        log.info("successfully confirmed the video upload date");
    }

    //Carson's stepdefs
    @Given("I am on the correct YouTube video page")
    public void correctYoutubePage() throws InterruptedException {
        super.setUp("chrome");

        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        videoPage.visit();
        driver.manage().window().maximize();
        Thread.sleep(5000);
        Assert.assertEquals("https://www.youtube.com/watch?v=lC0jzd8sGIA", videoPage.getCurrentUrl());
    }
    @When("I click on the share button")
    public void clickShareButton() throws InterruptedException {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        videoPage.clickShare(driver);
    }
    @And("I click on the embed button")
    public void clickEmbedButton() throws InterruptedException {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        videoPage.clickEmbed(driver);
    }
    @Then("I should get a window with the following html code")
    public void checkingEmbedTextCode() throws InterruptedException {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        Assert.assertEquals("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/lC0jzd8sGIA", videoPage.getEmbedText(driver).substring(0, 83));
    }

    @Given("I am on the embed YouTube video page")
    public void checkForEmbedPage(){
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        Assert.assertEquals("https://www.youtube.com/watch?v=lC0jzd8sGIA", videoPage.getCurrentUrl());
    }
    @When("I close the modal to get back to the main video page")
    public void closeSharePage(){
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        videoPage.exitShare(driver);
    }
    @And("I sort the comments by Newest First")
    public void sortComments() throws InterruptedException {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        videoPage.commentSectionHandler(driver);
        Thread.sleep(2000);
    }
    @Then("I should see the first comment was posted 1 week ago")
    public void viewNewestComment() throws InterruptedException {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
        videoPage.clickSortBy(driver);
        Thread.sleep(2000);
        videoPage.clickByNewest(driver);
    }
}