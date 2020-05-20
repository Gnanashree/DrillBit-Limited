@AdminLogin
Feature: Test Admin Account Scenarios
   Scenario: Test Admin Login and Other Functionality with valid Credentaials
    Given Open Chrome and Start application
    When I Enter valid username and password
    Then user should be able to login successfull
    And Click on Add User to Create Single User
    Then Verify Single User Creation is Successfull
    And Click on Add Multiple User to create Bulk Users
    Then Click on Edit Link to Edit User
    And Click on Delete Link to Delete an User
    Then Click on Download Reports to Download Document Submission Report
    And Then Download Folder Reports 
    Then Click on Direct Repository to upload files
    And Click on Support to Remove paper from Repository
    And Click Change password if you wish to change your password
    Then Click on Logout Link to Logout from Admin Account
    Then Close Browser
    
    
     
    
    