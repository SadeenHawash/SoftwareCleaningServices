Feature: Track Order Status

  Scenario: Update order status to "in treatment"
    Given a list of orders with status "WAITING"
    When an order is selected for treatment
    And the order status is updated to "IN_TREATMENT"
    Then the order status should reflect "IN_TREATMENT"

  Scenario: Update order status to "COMPLETE"
    Given a list of orders with status "IN_TREATMENT"
    When an order is selected as completed
    And the order status is updated to "COMPLETE"
    Then the order status should reflect "COMPLETE"