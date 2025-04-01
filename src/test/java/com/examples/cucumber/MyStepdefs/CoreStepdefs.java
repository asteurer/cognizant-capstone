package com.examples.cucumber.MyStepdefs;

import com.examples.cucumber.RunCucumberTest;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreStepdefs {

    //Protected driver and logger classes, for use in child classes
    protected static WebDriver driver;
    protected static Logger logger;

    /**
     * Setup function; must be called at the beginning of each test.
     *
     * @param browser
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(RunCucumberTest.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);

        //Ensure we can log in to YouTube using Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
    }
}
