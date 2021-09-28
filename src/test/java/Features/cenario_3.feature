Feature: Scenario Three

  Scenario: Scenario Three
    Given Open the Firefox and launch the application
    When The user search for '1984'
    Then Validate that the oldest comment published on the book is from '22/08/2018'
    And Validate that there are 0 ratings with 1 star