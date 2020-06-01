Feature: Login TC Login to the application

Scenario Outline: Negative test Successful login and navigating to next page
Given Initialising the driver
And Navigating to the <URL> site
When Click login link
And Log in with valid <userName> and <password>
Then Verify the error message is displayed
And Close Broswers
 
Examples:
|userName	|password	|URL	|
|ramyajit.bdd@yahoo.co.in	|abcd123	|http://qaclickacademy.com	|

