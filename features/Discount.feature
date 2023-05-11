Feature: Frequent Customer Discount

  Scenario: Customer qualifies for frequent customer discount
    Given a customer with name "John" and total spent amount of 500 NIS
    When the discount eligibility is checked
    Then the customer should receive a 10% discount
    And the discounted price should be calculated correctly

  Scenario: Customer does not qualify for frequent customer discount
    Given a customer with name "Jane" and total spent amount of 300 NIS
    When the discount eligibility is checked
    Then the customer should not receive a discount
    And the original price should be charged