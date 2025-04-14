Feature: Login Testing
  @Positive
  Scenario: Testing Correct Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email ...@gmail.com and password ...
    Then I should be signed in

  @Negative
  Scenario Outline: Testing Incorrect Password Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email <email> and password <password>
    Then I should receive a <warning type>
    Examples:
      | email         | password    | warning type     |
      | ...@gmail.com | "           | password warning |
      | ...@gmail.com | abcdefg     | password warning |
      | ...@gmail.com | 1234567890  | password warning |

  @Negative
  Scenario Outline: Testing Incorrect Email Login
    Given I am on a YouTube video page in the Chrome browser
    And I am not signed in
    When I sign in with email <email>
    Then I should receive an <warning type>
    Examples:
      | email                           | warning type    |
      | a                               | account warning |
      | $x%v!q845o1n@yahoo.com          | account warning |
      | test123456@yahoo.abcdefg        | account warning |
      | @2                              | email warning   |
      | abcdefg@4e3r8voqnkd5djzg6iv910  | email warning   |
      | ??????????@!!!!!!!!!!!!!!!.com  | email warning   |
      | ???????@yahoo.!!!               | email warning   |



