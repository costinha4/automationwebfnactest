Feature: Scenario Two

  Scenario: Scenario Two
    Given Open the Firefox and launch the application
    When The user search for '1984'
    Then Validate that the author is 'George Orwell'
    And Validate that the ISBN is '9789726081890'
    And Validate that the number of pages is '314'
    And Validate that the dimensions of the book are '13 x 21 cm'