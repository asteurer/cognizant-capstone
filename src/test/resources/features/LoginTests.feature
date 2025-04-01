Feature: Login Testing
  Scenario: Testing Correct Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with username ...@gmail.com and password ...
    Then I should be signed in

  Scenario: Testing Incorrect Password Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with username ...@gmail.com and password abcdefg
    Then I should receive a password warning