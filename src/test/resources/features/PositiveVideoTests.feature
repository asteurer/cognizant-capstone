Feature: Positive Video Page Testing
  Scenario: Comparing Embed Video HTML Code
    Given I am on the correct YouTube video page
    When I click on the share button
    And I click on the embed button
    Then I should get a window with the following html code

    Scenario: Test 2: Sorting Comments
      Given I am on the embed YouTube video page
      When I close the modal to get back to the main video page
      And I sort the comments by Newest First
      Then I should see the first comment was posted 1 week ago