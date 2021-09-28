Feature: Scenario One

  Scenario: Scenario One
    Given Open the Firefox and launch the application
    When The user search for 'George'
    Then Validate that the book 'Fascism and Democracy' is listed in the results
    And Validate that the book description contains the word 'world'