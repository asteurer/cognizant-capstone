package com.examples.cucumber.stepdefs;

import com.youtube.pageobjects.YouTubeLoginPage;
import com.youtube.pageobjects.YouTubeVideoPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepdefs extends CoreStepdefs {

    @When("I sign in with email {word} and password {word}")
    public void i_sign_in(String email, String password) {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Clicking on login link");
        videoPage.clickSignInButton();
        YouTubeLoginPage loginPage = new YouTubeLoginPage(driver);

        logger.info("Attempting to log in with given credentials");
        loginPage.login(email, password);
    }

    @When("I sign in with email {word}")
    public void i_sign_in(String email) {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Clicking on login link");
        videoPage.clickSignInButton();
        YouTubeLoginPage loginPage = new YouTubeLoginPage(driver);

        logger.info("Attempting to log in with given credentials");
        loginPage.enterEmail(email);
    }

    @Then("I should be signed in")
    public void i_should_be_signed_in() {
        YouTubeVideoPage videoPage = new YouTubeVideoPage(driver);

        logger.info("Verifying that we are logged in");
        Assert.assertTrue(videoPage.isAccountMenuVisible());
    }

    @Then("I should receive a password warning")
    public void i_should_receive_a_password_warning() {
        YouTubeLoginPage loginPage = new YouTubeLoginPage(driver);

        logger.info("Verify that the password error message is present");
        Assert.assertTrue(loginPage.isIncorrectPasswordLabelVisible());
    }

    @Then("I should receive an account warning")
    public void iShouldReceiveAnAccountWarning() {
        YouTubeLoginPage loginPage = new YouTubeLoginPage(driver);

        logger.info("Verify that the account error message is present");
        Assert.assertTrue(loginPage.isNoAccountLabelVisible());
    }

    @Then("I should receive an email warning")
    public void iShouldReceiveAnEmailWarning() {
        YouTubeLoginPage loginPage = new YouTubeLoginPage(driver);

        logger.info("Verify that the email error message is present");
        Assert.assertTrue(loginPage.isIncorrectEmailLabelVisible());
    }
}
