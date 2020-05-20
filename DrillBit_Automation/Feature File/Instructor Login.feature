@SingleFile
Feature: Test Instructor Account Scenarios
   Scenario: Test Instructor Login and Other Functionality with valid Credentaials 
    Given Open Chrome and Start application
    When I Enter valid username "gnanas844@gmail.com" and password "12345678"
    Then user should be able to login successfull to Instructor Account
    And Click on Create Folder Link to create an folder
    Then Verify Folder Creation is success with name "Single File Folder"
    And Click on Edit Link to Edit Options folder created 
    And Click on Select Link/Folder name to Upload Single File
    Then Verify Single file Upload is Success
    Then Close Browser     