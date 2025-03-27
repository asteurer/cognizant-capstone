Feature: YouTube Home Page Scenarios
  Scenario: Search for 'Cucumber Tests'
    Given I am on the Youtube home page youtube.com
    When I search for 'Cucumber Tests'
    Then I should find a link for 'Introduction to Cucumber'

  Scenario: Click on 'Cucumber Tests' video
    When I click on the link for the video
    Then I should see brought to the video page where it shows the date posted as May 14, 2017

#  Scenario: Click on share button and embed button
#    Given I am on the above page
#    When I click on the share button AND click on the Embed button
#    Then I should get a window with the following html code <iframe width="560" height="315" src="https://www.youtube.com/embed/lC0jzd8sGIA" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
#
#  Scenario: Sort comments by newest first
#    Given I am on the above modal page
#    When I close the modal to get back to the main video page AND sort the comments by “Newest First”
#    Then I should see the first comment was posted “1 week ago”