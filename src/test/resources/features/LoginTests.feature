Feature: Login Testing
  @Positive
  Scenario: Testing Correct Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email ...@gmail.com and password ...
    Then I should be signed in

  @Negative
  Scenario: Testing Incorrect Password Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email ...@gmail.com and password abcdefg
    Then I should receive a password warning

  @Negative
  Scenario: Testing Incorrect Email Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email $x%v!q845o1n@yahoo.com
    Then I should receive an account warning

  @Negative
  Scenario: Testing Non-Email Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email abcdefg@4e3r8voqnkd5djzg6iv910
    Then I should receive an email warning