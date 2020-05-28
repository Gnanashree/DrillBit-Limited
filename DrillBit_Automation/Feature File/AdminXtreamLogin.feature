@AdminLoginXtream
Feature: Test Xtream Admin Account Scenarios
   Scenario: Test Admin Login and Other Functionality with valid Credentaials
    Given Open Chrome and Start application
    When I Enter valid username "admin@xtream" and password "123456"    
    Then Xtream Admin user should be able to login successfull for application 
   # And Click on Download Student List to view student data
    #Then Verify Search filed in Student Navigation Page is working Successfull
    #And Click on Instructor Navigation Link to Add Single Instuctor
    #Then Verify User creation is successful or not
    #And Verify Search filed in Instructor Navigation Page is working Successfull
    #Then Click on Download Instructor List
    #Then Click on Edit filed to Edit user Information if Required
    #Then Click on Decativate or Activate for user
    #Then Click Delete if you want to remove user from account
    #Then Click on Download Reports to Download Document Submissions Report
    #Then Click on Class Reports 
    #Then Click on Assignments Reports 
    #Then Click on Instructors List to download 
    #And Student List to Download
    #Then Click on Direct Repository to upload files
    And Click on Support to Remove paper from Repository
   # And Click Change password if you wish to change your password
    Then Click on Logout Link to Logout from Admin Account
    #Then Close Browser
    
    
     
    
    