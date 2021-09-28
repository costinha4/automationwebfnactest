Feature: Scenario Five

  Scenario: Scenario Five
    Given Open the Firefox and launch the application
    And The user search for 'Fascism and Democracy'
    When The user adds book to shopping cart
    Then Validates that the number of items in the shopping cart is '1'
    And Validates that the total (with IVA) is equal to the book value plus estimated delivery costs