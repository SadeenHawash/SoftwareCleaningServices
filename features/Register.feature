Feature: Register customer
  Background:
    Given customers
      |id|customer name|password     |phone |address|
      |1| marah |12345     |0597823467 |bethlehem|
      |2|mohamad|56789     |0568923478 |nablus|


  @tag1
  Scenario: Register customer successful

    Given there is a customer with customer ID "id" ,NAME "customer name" , PASSWORD "pass " , phone "phone", address "address"
    When the customer is registered "customer name"
    Then the customer with ID "id" ,NAME "customer name" , PASSWORD "pass " , phone "phone", address "address" is registered in the system

  @tag2
  Scenario: a customer that is already registered

    Given there is a customer with customer ID "id" ,NAME "customer name" , PASSWORD "pass " , phone "phone", address "address"
    When the customer is registered "customer name"
    Then the error message "this customer is already registered" is given