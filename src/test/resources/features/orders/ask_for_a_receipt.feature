Feature: Ask for a receipt
Background:
  Given the following prices:
  |Product           |Price|
  |regular cappuccino|1.2  |
  |large cappuccino  |3.4  |
  |muffin            |3.4  |

Scenario: Order has several items
  Given Cathy has ordered:
  |Quantity           |Product         |
  |1                  |large cappuccino|
  |2                  |muffin          |
When shes asks for a receipt
Then she should receive a receipt totalling:
  |Subtotal|Service Fee|Total|
  |4.75    |0.24       |4.99 |
And the receipt should contain the line items:
  |Product          |Quantity   |Price|
  |large cappuccino |1          |2.25 |
  |muffin           |2          |2.50 |
