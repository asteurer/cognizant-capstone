Feature: Negative Video Page Testing
  @Positive
  Scenario: Subscribing while signed out
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I click the subscribe button
    Then I should see a sign-in subscription pop-up

  @Positive
  Scenario: Test 2: Opening settings menu while signed out
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I open the settings menu
    And I click the settings button
    Then I should be taken to the Google sign-in page