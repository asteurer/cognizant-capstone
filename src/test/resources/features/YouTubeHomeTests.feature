Feature: YouTube Home Page Scenarios
  @Positive
  Scenario: Youtube Home Page Scenario
    Given I am on the Youtube home page youtube.com
    When I search for 'Cucumber Tests'
    Then I should find a link for 'Introduction to Cucumber'
    When I click on the link for the video
    Then I should see brought to the video page where it shows the date posted as May 14, 2017