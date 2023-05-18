Feature: Customer Actions
  Background: View Customer Page List
    Given customer log in with true "id" and "password"

  Scenario: Update My Profile
    Given I am logged in
    When I navigate to My Profile
    And I update my profile information depends on 1 and "value"
    Then my profile should be updated successfully

  Scenario: Make an Order
    Given I am logged in
    When I navigate to the Order page
    And I select the desired items
    Then the order should be placed successfully

  Scenario: View Invoices
    Given I am logged in
    When I navigate to Invoices
    Then I should see a my invoice

  Scenario: Delete My Profile
    Given I am logged in
    When I navigate to My Profile
    And I choose to delete my profile
    And I confirm the deletion
    Then my profile should be deleted successfully

