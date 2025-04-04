package com.examples.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.examples.cucumber.stepdefs"},
        plugin = {"pretty", "html:YouTubeReport.html"},
        tags = "@Positive or @Negative"
)
public class RunCucumberTest {

}
