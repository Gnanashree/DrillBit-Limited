@MultipleFile
Feature: Test Instructor Account Scenarios
   Scenario: Test Instructor Login and Other Functionality with valid Credentaials 
    Given Open Chrome and Start application
    When I Enter valid username "gnanas844@gmail.com" and password "12345678"
    Then user should be able to login successfull to Instructor Account
    And Click on Create Folder Link to create with name Multiple File Folder
    Then Verify Folder Creation is success with name Multiple File Folder
    And Click on Select Link or Folder name to Upload Multiple File
    Then Verify Multiple file Upload is Success
    And then Click on Add More Files to upload
    And Click on Download Folder Report in Instructor Acc
    Then Validate Search Filed by Author Name
    Then Close Browser
    
    
