package com.examples.cucumber.MyStepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyStepdefs {
    @Given("I am on the Youtube home page youtube.com")
    public void ytHomePage() {

    }

    @When("I search for \"Cucumber Tests\"")
    public void searchVideo() {

    }

    @Then("I should find a link for \"Introduction to Cucumber\"")
    public void findVideoLink() {

    }

    @When("I click on the link for the video")
    public void clickVideoLink(){

    }

    @Then("I should see brought to the video page where it shows the date posted as May 14, 2017")
    public void confirmVideoDate() {

    }
}