Feature: Order T-Shirt Test
Description : This feature is to test Ordering an T-Shirt and verify the same orders history page

Scenario: Order an T-Shirt and Verify in order history page
	Given User is in shopping site
	When user searches for "T-Shirt"
	And select the product
	And add the product to the cart
	And choose to checkout the products in the cart
	And choose to checkout in summary page
	And Sign in with username "k.karthick93@gmail.com" and password "Password123"
	And choose to checkout in address page
	And agree the terms to proceed to checkout
	And select the payment type
	And confirm the order in payment page
	Then Order details will be displayed
	And the Order is displayed in order history page
	
	