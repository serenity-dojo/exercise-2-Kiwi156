Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee love
  I want to be able to order my coffee in advance

  Scenario: Buyer orders a coffee when they are really close to the coffee shop
    Given Cathy is 1.5 metre from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Urgent

  Scenario: Buyer orders a coffee when they are getting close to the coffee shop
    Given Cathy is 300 metres from the coffee shop
    When Cathy orders an "americano"
    Then Barry should receive the order
    And Barry should know that the order is Normal

  Scenario: Buyer orders a coffee when they are close to the coffee shop
    Given Cathy is 100 metres from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Urgent

