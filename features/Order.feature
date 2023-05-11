Feature: Ordering a product to clean
  Scenario: Make an order
    Given the customer is going to make an order
    When the customer enters the product information name, material, area,treatment, picture
    Then a new order should be registered
