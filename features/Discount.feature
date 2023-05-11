Feature: Cleaning system discount


  Scenario: Discount to the customer by the price
    Given the user is login with email "ayat@gmail.com"
    And the price of the user "300"
    Then the user pay the price of product after discount 10% "300"