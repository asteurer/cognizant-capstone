package com.examples.cucumber.stepdefs;

import com.youtube.pageobjects.YouTubeVideoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NegativeVideoTestStepdefs extends CoreStepdefs {

    /**
     * Setup function for our browser and logger. Note that depending on the setup of our Cucumber tests, it may be
     * easiest to use this without annotations.
     *
     * @param browser
     *
    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {

        logger = Logger.getLogger(YouTubeVideoPage.class.getName());
        logger.setLevel(Level.INFO);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

        switch (browser.toLowerCase()) {
            case "chrome":
                logger.info("Testing with " + browser + " browser");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                logger.warning("Configuration missing for browser \"" + browser + "\", defaulting to Chrome.");
                driver = new ChromeDriver();
                break;
        }
    }*/

    @Given("I am on a YouTube video page in the {word} browser")
    public void on_youtube_video_page(String browser) {
        super.setUp(browser);

        logger.info("Opening page");
        driver.get("https://www.youtube.com/watch?v=P7gt3g7iq7M");
    }

    /**
     * This step ensures that we are not signed in; if we are, sign out.
     */
    @Given("I am not signed in")
    public void not_signed_in() {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        //TODO: This section is flawed; will make us wait 10 sec each time if we are signed in
        logger.info("Ensure that Sign In button is visible in top right");
        if(!videoPage.isSignInButtonVisible()) {
            logger.info("Sign In button is not visible; logging out");

            logger.info("If settings menu is visible, open it");
            videoPage.clickSettingsMenu();

            logger.info("Click sign out button");
            videoPage.clickSignOut();
        }
    }

    @When("I click the subscribe button")
    public void click_the_subscribe_button() {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Click the subscribe button");
        videoPage.clickSubscribeButton();
    }

    @Then("I should see a sign-in subscription pop-up")
    public void verify_sign_in_subscription_pop_up() {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Assert that the YouTube subscription pop-up appears");
        Assert.assertTrue(videoPage.isSubscriptionPopUpVisible());

        logger.info("Verify that pop-up has the correct text");
        Assert.assertEquals("Sign in to subscribe to this channel.", videoPage.getSubscriptionPopUpText());
    }

    @When("I open the settings menu")
    public void open_settings_menu() {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Opening the settings menu");
        videoPage.clickSettingsMenu();
    }

    @When("I click the settings button")
    public void click_settings_button() {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Clicking the settings button in the settings menu");
        videoPage.clickSettingsButton();
    }

    @Then("I should be taken to the Google sign-in page")
    public void verify_google_sign_in_page() throws InterruptedException {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Verifying that we are taken to the sign-in page");
        Thread.sleep(2000);
        boolean isRightPage = videoPage.getCurrentUrl().startsWith("https://accounts.google.com/v3/signin");
        Assert.assertTrue(isRightPage);
    }
}