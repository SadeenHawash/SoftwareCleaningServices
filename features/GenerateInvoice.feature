Feature: Generate Customer Invoice
  Scenario: Generate an invoice for a customer order
    Given a customer with a list of items to be cleaned
    And an invoice is generated
    Then the invoice should include the delivery information, price, address, and items to be cleaned
