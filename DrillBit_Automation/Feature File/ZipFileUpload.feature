@ZipFile
Feature: Test Instructor Account Scenarios
   Scenario: Test Instructor Login and Other Functionality with valid Credentaials 
    Given Open Chrome and Start application
    When I Enter valid username "gnanas844@gmail.com" and password "12345678"
    Then user should be able to login successfull to Instructor Account
    And Click on Create Folder Link to create an folder with name Zip File Folder
    Then Verify Folder Creation is success with name Zip File Folder
    And Click on Select Link or Folder name to Upload Zip File
    Then Verify Zip file Upload is Success
    And to validate delete functionality create an dummy folder
    And then validate Search Field by navigating back to Home Page
    Then Click on Account info to view user deatils
    And Click on Recently Deleted Link to view deleted documents
    And also Click on View Repository to see the files saved
    And Click on Change Password Link to validate that functionality
    Then validate Logout Link in Instructor Acc
    Then Close Browser
   
    
    
