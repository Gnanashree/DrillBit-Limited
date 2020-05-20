@ResultSheet
Feature: Test Result Sheet or Page
   Scenario: Test Result Sheet of Paper uploaded 
    Given Open Chrome and Start application
    When I Enter valid username "gnanas844@gmail.com" and password "12345678"
    Then user should be able to login successfull to Instructor Account
   # And Clik on folder name or Select link to navigate to submission page
    Then Click on Download link to view result 
    And Click on Delete button to delete any file or document
   And Click on Similiarty percenatge to view in deatils
  #  Then again Click on Download Tab in result sheet to view
    And Click on Save to Repository less than 30 percenatge
    And Clcik on Email Notification tab
    Then Click on Exclude or Include Data
    Then Click on Exit
    
    
    
    
