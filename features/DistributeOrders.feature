Feature: Distribute Orders on Workers

  Scenario: Distribute orders on available workers
    Given there are waiting orders
    And there are available workers
    When the orders are distributed
    Then each worker should receive an equal number of orders
    And the status of the workers and orders should be updated
