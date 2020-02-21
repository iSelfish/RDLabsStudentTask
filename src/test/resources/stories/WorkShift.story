Narrative:
As a user
I want to make sure that all functionality on Work Shifts page as expected

Lifecycle:
Before:
Given I am on the login page of application
And I login to application with username 'admin' and password 'admin123'
And I go to Work Shifts page

!-- https://jbehave.org/reference/latest/parameter-converters.html

!-- TODO implement this scenario
Scenario: AC-1 Check that by default General and Twilight work shifts types are shown on work shifts page
Meta: @regression
Then Rows in Work Shift column have General, Twilight default values

!-- TODO implement this scenario
Scenario: AC-2 Check that Work Shift field on Add work shift model requiired
Meta: @regression
When I click on Add Work Shift button
And I click on Save button in Add Work Shift window
Then Required error message is shown under Work Shift field

!-- TODO implement this scenario
Scenario: AC-3 Check that value in Hours Per Day field calculated propertly
Meta: @regression
When I click on Add Work Shift button
And Using time picker set 10:50 value into From filed
And Using time picker set 18:20 value into To filed
Then 7.50 value is calculated in Hours Per Day field
When Using time picker set 8:05 value into From filed
And Using time picker set 20:25 value into To filed
Then 12.33 value is calculated in Hours Per Day field