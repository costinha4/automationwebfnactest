Feature: Scenario Six

  Scenario: Scenario Six
    Given Open the Firefox and launch the application
    And The user find a 'Lisboa' store
    When The user select the 'Almada' store, from the list of results
    Then Check that the store's zip code is '2810-354'
    And Validates that the store is open every day between '10:00' and '23:00'