Feature: Notify customer by email when order is complete
  Scenario: Customer is notified by email when their order is complete
    Given a customer has placed an order
    When the order is complete
    Then the customer should receive an email notification