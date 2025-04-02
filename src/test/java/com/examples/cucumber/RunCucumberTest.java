package com.examples.cucumber;

import com.youtube.pageobjects.YouTubeVideoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;


import java.util.logging.Level;
import java.util.logging.Logger;

public class RunCucumberTest {

    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(RunCucumberTest.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
                driver = new ChromeDriver();
                break;
        }
    }
//    @Test
//    public void testVideoPage() throws InterruptedException {
//        logger.info("Starting Video Page Functionality");
//        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);
//        videoPage.visit();
//        driver.manage().window().maximize();
//        Thread.sleep(5000);
//        logger.info("Checking Page");
//        Assert.assertEquals("https://www.youtube.com/watch?v=lC0jzd8sGIA", videoPage.getCurrentUrl());
//        logger.info("Clicking Share");
//        videoPage.clickShare(driver);
//        logger.info("Clicking Embed");
//        videoPage.clickEmbed(driver);
//        logger.info("Comparing Embed Video Code");
//        //Assert.assertEquals("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/lC0jzd8sGIA?si=lAZ57pi1NmisWylG\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", videoPage.getEmbedText(driver));
//        logger.info("Closing Share Popup");
//        videoPage.exitShare(driver);
//
//        logger.info("Sorting Comments by Newest First");
//        //TODO: Sort comment function and locate the newest comment
//        videoPage.commentSectionHandler(driver);
//        Thread.sleep(2000);
//        videoPage.clickSortBy(driver);
//        Thread.sleep(2000);
//        videoPage.clickByNewest(driver);
//    }

    @Given("I am on the correct YouTube video page")
    public void correctYoutubePage() throws InterruptedException {
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

    @Given("I am on the embed YouTube video Page")
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

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }



}
