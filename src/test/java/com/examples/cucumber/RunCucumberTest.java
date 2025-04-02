package com.examples.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.examples.cucumber.stepdefs"},
        plugin = {"pretty", "html:YouTubeReport"},
        tags = "@Positive or @Negative"
)
public class RunCucumberTest {

    /*
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

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }*/


}
