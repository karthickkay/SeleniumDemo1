Feature: Update Personal Information
Description : This feature is to test update personal information functionality in My Account page

Scenario: Update First Name in personal information my account page
	Given User is in shopping site
	And User navigates to Sign in page
	And Sign in with username "k.karthick93@gmail.com" and password "Password123"
	And User navigates to My Personal Information page
	And Update the First Name as "Arun" with current password as "Password123"
	When User clicks save
	Then Personel Information is updated