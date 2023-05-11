Feature: Generate Business Statistics

  Scenario: Calculate total delivered items
    Given a list of all completed orders
    When the total number of delivered items is calculated
    Then the correct total number of delivered items should be displayed

  Scenario: Calculate total cash
    Given a list of all completed orders
    When the total cash collected is calculated
    Then the correct total cash amount should be displayed

  Scenario: Calculate total paid
    Given a list of all completed orders
    When the total amount paid by customers is calculated
    Then the correct total paid amount should be displayed

  Scenario: Calculate total debts
    Given a list of all completed orders
    When the total amount owed by customers is calculated
    Then the correct total debts amount should be displayed