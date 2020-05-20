package Step_Defination_Class;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Steps {
	WebDriver chrome=null;
	//admin account validation
	@Given("^Open Chrome and Start application$")
	public void open_Chrome_and_Start_application() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\DrillBit SoftTech\\DrillBit_Automation\\Library\\Chrome\\chromedriver.exe");
		chrome=new ChromeDriver();
		chrome.manage().window().maximize();
		chrome.get("http://104.215.191.211:8080/drillbit_new/drillbitlogin");
		String url_navigation=chrome.findElement(By.xpath("//button[text()='Login']")).getText();
		Assert.assertTrue("Fail:->DrillBit Anti Palgarisim URL navigation was not success", url_navigation.equals("Login"));
		System.out.println("Message->DrillBit Anti Palgarisim URL Link  Navigated is Successfully");  
	}
	
	
	@When("^I Enter valid username and password$")
	public void i_Enter_valid_username_and_password() throws Throwable {
		chrome.findElement(By.xpath("//input[@name='username']")).sendKeys("test@gmail.com");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(2000);    
	}
	
	@Then("^user should be able to login successfull$")
	public void user_should_be_able_to_login_successfull() throws Throwable {
		String login=chrome.findElement(By.xpath("//p[text()='Admin Dashboard']")).getText();
		Assert.assertTrue("Fail Message-> Admin Login to Application is not success",login.equals("Admin Dashboard"));
		System.out.println("Pass Message-> Admin Login to Application is success");
	}
	
	@And("^Click on Add User to Create Single User$")
	public void click_on_Add_User_to_Create_Single_User() throws Throwable {
		chrome.findElement(By.xpath("//a[text()='Users']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//a[text()='Add User']")).click();
		Thread.sleep(2000);
		//WebElement User_max_limit=chrome.findElement(By.xpath("//span[text()='Sorry! Reached the maximum limit !! No user accounts left']"));
		//String limit_user=chrome.findElement(By.xpath("//span[text()='Sorry! Reached the maximum limit !! No user accounts left']")).getText();
		//if(!User_max_limit.isDisplayed()) {
			// Create new users If maximum limit not reached 
		    chrome.findElement(By.xpath("//input[@name='name']")).sendKeys("ABCD");
			Thread.sleep(2000);
			chrome.findElement(By.xpath("//input[@name='username']")).sendKeys("abcd@gmail.com");
			Thread.sleep(2000);
			chrome.findElement(By.xpath("//input[@name='count']")).clear();
			chrome.findElement(By.xpath("//input[@name='count']")).sendKeys("5");
			Thread.sleep(2000);
			chrome.findElement(By.xpath("//button[text()='Confirm']")).click();
			Thread.sleep(2000);
			//alert 
			if(isAlertPresent_Createuser(chrome))
			{
				System.out.println("Create Single User-> Alert exist");
                chrome.switchTo().alert().accept();
			}
			else {
				System.out.println("Create Single  User-> Alert doesnot exist");
			}
			
		/*} else if(User_max_limit.isDisplayed()) {
			System.out.println("User creation  Maximum limit exceded ->"+limit_user);
		}	*/	
	}
	
	static boolean isAlertPresent_Createuser(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@Then("^Verify Single User Creation is Successfull$")
	public void verify_Single_User_Creation_is_Successfull() throws Throwable {
		String search=chrome.findElement(By.xpath("//td[text()='ABCD']")).getText();
		Assert.assertTrue("Fail Message:-> User not Found",search.equals("ABCD"));
		System.out.println("Pass Message-> User " + search +" Created Successfully");
		}
	
	@Then("^Click on Add Multiple User to create Bulk Users$")
	public void click_on_Add_Multiple_User_to_create_Bulk_Users() throws Throwable {
		chrome.findElement(By.xpath("//a[text()='Users']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//button[text()='Add Multiple Users']")).click();
		Thread.sleep(2000);
		WebElement ch_file=chrome.findElement(By.xpath("//input[@name='file']"));
		ch_file.sendKeys("C:\\Users\\Pragna\\Desktop\\userTemplate .xlsx");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@value='Submit']")).click();
		//alert 
		if(isAlertPresent_Createuser_Multiple(chrome))
		{
			System.out.println("Create Bulk User-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Create Bulk User-> Alert doesnot exist");
		}	
		String Bulk_User=chrome.findElement(By.xpath("//span[contains(text(),'An Invitation Email will be sent to')]")).getText();
		Assert.assertTrue("Fail Message-> Bulk Multiple Users not able to Create", Bulk_User.contains("An Invitation Email will be sent to"));
		System.out.println("Pass Message-> Bulk Multiple Users Created");
		chrome.findElement(By.xpath("//a[text()='Users']")).click();
	}
	static boolean isAlertPresent_Createuser_Multiple(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@Then("^Click on Edit Link to Edit User$")
	public void click_on_Edit_Link_to_Edit_User() throws Throwable {
	    chrome.findElement(By.xpath("//td[text()=' Demo 1']/ancestor::tr/td[6]/a[text()='Edit']")).click();
	    Thread.sleep(2000);
	    chrome.findElement(By.xpath("(//input[@id='Text'])[1]")).clear();
	    Thread.sleep(2000);
	    chrome.findElement(By.xpath("(//input[@id='Text'])[1]")).sendKeys("User Demo 1");;
	    Thread.sleep(2000);
	    chrome.findElement(By.xpath("(//input[@id='count'])")).clear();
	    Thread.sleep(2000);
	    chrome.findElement(By.xpath("(//input[@id='count'])")).sendKeys("5");
	    Thread.sleep(2000);
	    chrome.findElement(By.xpath("//button[text()='Confirm']")).click();
		Thread.sleep(2000);
		//alert 
		if(isAlertPresent_Createuser_edit(chrome))
		{
			System.out.println("Create Single User Edit-> Alert exist");
            chrome.switchTo().alert().accept();
		}
		else {
			System.out.println("Create Single  User Edit-> Alert doesnot exist");
		}	
		String Edit_User=chrome.findElement(By.xpath("//span[text()='User has been updated successfully!']")).getText();
		Assert.assertTrue("Fail Message-> Unable to Edit User Deatils", Edit_User.equals("User has been updated successfully!"));
		System.out.println("Pass Message-> User Details Has been Edited/Updated Successfully");
}

static boolean isAlertPresent_Createuser_edit(WebDriver chrome)
{
	try {
		chrome.switchTo().alert();
		return true;
	}catch(NoAlertPresentException e)
	{
		return false;
	}
}
	@Then("^Click on Delete Link to Delete an User$")
	public void click_on_Delete_Link_to_Delete_an_User() throws Throwable {
		chrome.findElement(By.xpath("//td[text()='Demo User 2']//ancestor::tr/td[6]/a[text()='Delete']")).click();
		Thread.sleep(2000);
		//alert 
		if(isAlertPresent_User_Delete(chrome))
		{
			System.out.println("Delete Single User-> Alert exist");
            chrome.switchTo().alert().accept();
		}
		else {
			System.out.println("Delete Single User-> Alert doesnot exist");
		}	
		String Delete_user=chrome.findElement(By.xpath("//span[text()='User has been deleted successfully']")).getText();
		Assert.assertTrue("Fail Message-> Unable to Delete USer", Delete_user.equals("User has been deleted successfully"));
		System.out.println("Pass Message-> User Deleted Successfully!!");
}

static boolean isAlertPresent_User_Delete(WebDriver chrome)
{
	try {
		chrome.switchTo().alert();
		return true;
	}catch(NoAlertPresentException e)
	{
		return false;
	}
}

	@Then("^Click on Download Reports to Download Document Submission Report$")
	public void click_on_Download_Reports_to_Download_Document_Submission_Report() throws Throwable {
		chrome.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
		Thread.sleep(2000);
		Select sel=new Select(chrome.findElement(By.name("instructor")));
		Thread.sleep(2000);
		sel.selectByVisibleText("All Users");
		Thread.sleep(2000);
		//sel.selectByVisibleText("gnanass1995@gmail.com");
		//Thread.sleep(2000);
		//sel.selectByVisibleText("praveenmb.19@gmail.com");
		//Thread.sleep(2000);
		WebElement date=chrome.findElement(By.xpath("(//input[@name='date1'])[1]"));
		date.sendKeys("01032020");
		Thread.sleep(2000);
		date.sendKeys(Keys.TAB);
		
		//to date
		WebElement date1=chrome.findElement(By.xpath("(//input[@name='date2'])[1]"));
		date1.sendKeys("31032020");
		Thread.sleep(2000);
		date1.sendKeys(Keys.TAB);
		chrome.findElement(By.xpath("(//input[@type='submit'])[1]")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@value='Download Excel Report']")).click();
		//alert
		if(isAlertPresent_Download_submission_repo(chrome))
		{
			System.out.println("Download Document Submission Report-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Download Document Submission Report-> Alert doesnot exist");
		}
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//u[text()='DashBoard']")).click();	
	}
	static boolean isAlertPresent_Download_submission_repo(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	    
	@Then("^Then Download Folder Reports$")
	public void then_Download_Folder_Reports() throws Throwable {
		chrome.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
		Thread.sleep(2000);

		Select sel1=new Select(chrome.findElement(By.xpath("(//select[@name='instructor'])[2]")));
		Thread.sleep(2000);
		sel1.selectByVisibleText("All Users");
		Thread.sleep(2000);
		//sel1.selectByVisibleText("gnanass1995@gmail.com");
		//Thread.sleep(2000);
		//sel1.selectByVisibleText("gnanass1995@gmail.com");
		//Thread.sleep(2000);
		//sel1.selectByVisibleText("praveenmb.19@gmail.com");
		//Thread.sleep(4000);
		WebElement date3=chrome.findElement(By.xpath("(//input[@name='date1'])[2]"));
		date3.sendKeys("01042020");
		Thread.sleep(2000);
		date3.sendKeys(Keys.TAB);
		//to date
		WebElement date4=chrome.findElement(By.xpath("(//input[@name='date2'])[2]"));
		date4.sendKeys("30042020");
		Thread.sleep(2000);
		date4.sendKeys(Keys.TAB);
		chrome.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@type='button']")).click();
		Thread.sleep(2000);

		//alert
		if(isAlertPresent_Download_folder_repo(chrome))
		{
			System.out.println("Download Folder Report-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Download Folder Report-> Alert doesnot exist");
		}
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//u[text()='DashBoard']")).click();
		
	}
	static boolean isAlertPresent_Download_folder_repo(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@Then("^Click on Direct Repository to upload files$")
	public void click_on_Direct_Repository_to_upload_files() throws Throwable {
		chrome.findElement(By.xpath("//a[contains(text(),'Direct')]")).click();
		Thread.sleep(4000);
		chrome.findElement(By.xpath("//input[@name='name']")).sendKeys("Repo_1");
		Thread.sleep(4000);
		chrome.findElement(By.xpath("//input[@name='title']")).sendKeys("Repo_File_1");
		Thread.sleep(4000);
		WebElement elem = chrome.findElement(By.xpath("//input[@name='repo_file']"));
		Thread.sleep(2000);
		// 'type' the file location to it as it were a usual <input type='text' /> element
		elem.sendKeys("C:\\DRILLBIT_DOC\\Encapsulation Concepts.docx");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@value='Submit']")).click();
		//alert
		
		if(isAlertExist_dir_repo(chrome))
		{
			System.out.println("File Upload for Direct Repository-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("File Upload for Direct Repository-> Alert doesnot exist");
		}
		String File_Save_dir_Repo=chrome.findElement(By.xpath("//span[text()='File has been uploded to the Repository.']")).getText();
		Assert.assertTrue("Fail Message-> File not Uploaded Successfully",
				File_Save_dir_Repo.equals("File has been uploded to the Repository."));
		System.out.println("Pass Message-> File has been uploded to the Repository. ");
	}
	
	static boolean isAlertExist_dir_repo(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}
	
	@Then("^Click on Support to Remove paper from Repository$")
	public void click_on_Support_to_Remove_paper_from_Repository() throws Throwable {
		chrome.findElement(By.xpath("//a[text()='Support']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='paper_id']")).sendKeys("101");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("(//a[text()='Remove from repository'])[1]")).click();
		Thread.sleep(2000);
		if(isAlertExist_Support(chrome))
		{
			System.out.println("Remove Paper from Repository-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Remove Paper from Repository-> Alert doesnot exist");
		}
		String File_remove_From_Repo=chrome.findElement(By.xpath("//span[text()='File successfully removed from the repository.']")).getText();
		Assert.assertTrue("Fail Message-> File successfully not removed from the repository.",
				File_remove_From_Repo.equals("File successfully removed from the repository."));
		System.out.println("Pass Message-> File successfully removed from the repository.");

	}
	
	static boolean isAlertExist_Support(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}
	
	@Then("^Click Change password if you wish to change your password$")
	public void click_Change_password_if_you_wish_to_change_your_password() throws Throwable {
		chrome.findElement(By.xpath("//a[text()='Change Password']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='expass']")).sendKeys("12345678");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='password1']")).sendKeys("12345678");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='password2']")).sendKeys("12345678");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@value='Change Password']")).click();
		Thread.sleep(2000);
		if(isAlertExist_ChangePassword(chrome))
		{
			System.out.println("Change Password-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Change Password-> Alert doesnot exist");
		}
		String Logout=chrome.findElement(By.xpath("//span[text()='Action success! Your new password has updated successfully']")).getText();
		Assert.assertTrue("Fail Message-> Action failed! Your new password has not been  updated successfully",
				Logout.equals("Action success! Your new password has updated successfully"));
		System.out.println("Pass Message-> Action success! Your new password has updated successfully");
	}
	
	static boolean isAlertExist_ChangePassword(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}
	
	@Then("^Click on Logout Link to Logout from Admin Account$")
	public void click_on_Logout_Link_to_Logout_from_Admin_Account() throws Throwable {
		chrome.findElement(By.xpath("//a[text()='Logout']")).click();
		Thread.sleep(2000); 
		String Logout=chrome.findElement(By.xpath("//div[contains(text(),' Successfully Logged out')]")).getText();
		Assert.assertTrue("Fail Message-> User Could not logout from Application",
				Logout.equals("Successfully Logged out"));
		System.out.println("PAss Message-> User Logut from application Was Successfull");
	}

	@Then("^Close Browser$")
	public void close_Browser() throws Throwable {
		chrome.close();    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//End of admin account validation
	
	//Start of Instructor account validation
	
	@When("^I Enter valid username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_Enter_valid_username_and_password(String UserName, String Password) throws Throwable {
		chrome.findElement(By.xpath("//input[@name='username']")).sendKeys(UserName);
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(2000);    
	}
	
	@Then("^user should be able to login successfull to Instructor Account$")
	public void user_should_be_able_to_login_successfull_to_Instructor_Account() throws Throwable {
		String login_instruct=chrome.findElement(By.xpath("//button[text()='Home']")).getText();
		Assert.assertTrue("Fail Message-> Instructor Login to Application is not success",login_instruct.equals("Home"));
		System.out.println("Pass Message-> Instructor Login to Application is success");
	}
	
	@Then("^Click on Create Folder Link to create an folder$")
	public void click_on_Create_Folder_Link_to_create_an_folder() throws Throwable {
		chrome.findElement(By.xpath("//button[text()='Create Folder']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@name='assignment_name']")).sendKeys("Single File Upload");
		Thread.sleep(2000);
		WebElement dateBox=chrome.findElement(By.xpath("//input[@name='end_date']"));
        dateBox.sendKeys("29052020");
        Thread.sleep(2000);
        dateBox.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        
        //**Options selection**/
        chrome.findElement(By.xpath("//td[contains(text(),'Reference')]/ancestor::tr/descendant::input[@value='yes']")).click();
        Thread.sleep(2000);
        chrome.findElement(By.xpath("//td[contains(text(),'Quotes')]/ancestor::tr/td/label/input[@value='yes']")).click();
        Thread.sleep(2000);
        chrome.findElement(By.xpath("//td[contains(text(),'small')]/ancestor::tr/td/label/input[@value='no']")).click();
        Thread.sleep(2000);
        chrome.findElement(By.xpath("//td[contains(text(),'Option')]/ancestor::tr/td/label/input[@value='yes']")).click();
        Thread.sleep(2000);
        chrome.findElement(By.xpath("//input[@value='submit']")).click();
       Thread.sleep(2000);
       //**Alert msg for folder creation**/
		if(isAlertPresent(chrome))
		{
			System.out.println("Folder_Creation_Alert:Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Folder_Creation_Alert: Alert doesnot exist");
		}
		
	
	}
	static boolean isAlertPresent(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@Then("^Verify Folder Creation is success with name \"([^\"]*)\"$")
	public void verify_Folder_Creation_is_success_with_name(String folderName) throws Throwable {
		String txt_folder=chrome.findElement(By.xpath("//span[text()='Single File Upload']")).getText();
		Assert.assertTrue("Fail: Folder not created", txt_folder.equals("Single File Upload"));
		System.out.println("Pass-> Folder name as " +txt_folder+ " created successfully!!!");
	}
	
	@Then("^Click on Edit Link to Edit Options folder created$")
	public void click_on_Edit_Link_to_Edit_Options_folder_created() throws Throwable {
		chrome.findElement(By.xpath("//span[text()='Single File Upload']/parent::a/parent::td/following-sibling::td[4]/div[@class='dropdown']/button[contains(text(),'Edit')]/i")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//span[text()='Single File Upload']/parent::a/parent::td/following-sibling::td[4]/div[@class='dropdown open']/div/a[contains(text(),'Edit ')]")).click();
		System.out.println("Edit Setting link cliked successfully");
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//td[contains(text(),'small')]/ancestor::tr/td/label/input[@value='yes']")).click();
		Thread.sleep(2000);
		chrome.findElement(By.xpath("//input[@value='submit']")).click();
		Thread.sleep(2000);
		//**Alert for edited filed**/
		if(isAlertPresent_ed(chrome))
 		{
 			System.out.println("Edited Folder-> Alert exist");
 			chrome.switchTo().alert().accept();
 		}else {
 			System.out.println("Edited Folder-> Alert doesnot exist");
 		}
		String Edit_folder=chrome.findElement(By.xpath("//span[text()='Action Success! Updated successfully']")).getText();
		Assert.assertTrue("Fail Message->Action Failed! Unable to Edit folder successfully",
				Edit_folder.equals("Action Success! Updated successfully"));
		System.out.println("Pass Message-> Action Success! Updated/Edited successfully");
	}

static boolean isAlertPresent_ed(WebDriver chrome)
{
	try {
		chrome.switchTo().alert();
		return true;
	}catch(NoAlertPresentException e)
	{
		return false;
	}
}

@Then("^Click on Select Link/Folder name to Upload Single File$")
public void click_on_Select_Link_Folder_name_to_Upload_Single_File() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Single File Upload']")).click();
	Thread.sleep(3000);
	chrome.findElement(By.xpath("//button[text()='Upload']")).click();
	Thread.sleep(3000);
	chrome.findElement(By.xpath("//a[text()='Single File']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@name='name']")).sendKeys("File 1");
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@name='title']")).sendKeys("Paper 1");
	Thread.sleep(2000);
	// find the input element
	WebElement elem = chrome.findElement(By.xpath("//input[@type='file']"));
	Thread.sleep(2000);
	// 'type' the file location to it as it were a usual <input type='text' /> element
	elem.sendKeys("C:\\DRILLBIT_DOC\\Files\\Narsimhlu paper_DB.docx");
	//elem.sendKeys("C:\\DRILLBIT_DOC\\Files\\Employee.pdf");
	//elem.sendKeys("C:\\DRILLBIT_DOC\\Files\\Brainy Health Care - Copy.doc");
   //elem.sendKeys("C:\\DRILLBIT_DOC\\Files\\Testing_DOc_Txt.txt");
	chrome.findElement(By.xpath("//button[text()='Submit']")).click();
	//Verify alert exist
	if(isAlertExist_single_file_upload(chrome)==true) {
		System.out.println("Single_File_Alert: Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Single_File_Alert: No Alert was found");
	}
	
	}
static boolean isAlertExist_single_file_upload(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}

@Then("^Verify Single file Upload is Success$")
public void verify_Single_file_Upload_is_Success() throws Throwable {
	String single_file_validate=chrome.findElement(By.xpath("//span[contains(text(),'You successfully uploaded')]")).getText();
	Assert.assertTrue("Fail: Single file not uploaded",single_file_validate.contains("You successfully uploaded"));
	System.out.println("Pass: Single file upoaded successfuly");
}

//Start of multiple file upload
@When("^Click on Create Folder Link to create with name Multiple File Folder$")
public void click_on_Create_Folder_Link_to_create_with_name_Multiple_File_Folder() throws Throwable {
	chrome.findElement(By.xpath("//button[text()='Create Folder']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@name='assignment_name']")).sendKeys("Multiple File Folder_dummy");
	Thread.sleep(2000);
	WebElement dateBox=chrome.findElement(By.xpath("//input[@name='end_date']"));
    dateBox.sendKeys("29052020");
    Thread.sleep(2000);
    dateBox.sendKeys(Keys.TAB);
    Thread.sleep(2000);
    
    //**Options selection**/
    chrome.findElement(By.xpath("//td[contains(text(),'Reference')]/ancestor::tr/descendant::input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'Quotes')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'small')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'Option')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@value='submit']")).click();
   Thread.sleep(2000);
   //**Alert msg for folder creation**/
	if(isAlertPresent_folderCreate_2(chrome))
	{
		System.out.println("Folder Creation-> Alert exist");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Folder Creation-> Alert doesnot exist");
	}
}
static boolean isAlertPresent_folderCreate_2(WebDriver chrome)
{
	try {
		chrome.switchTo().alert();
		return true;
	}catch(NoAlertPresentException e)
	{
		return false;
	}
}



@Then("^Verify Folder Creation is success with name Multiple File Folder$")
public void verify_Folder_Creation_is_success_with_name_Multiple_File_Folder() throws Throwable {
	String txt_folder=chrome.findElement(By.xpath("//span[text()='Multiple File Folder_dummy']")).getText();
	Assert.assertTrue("Fail: Folder not created", txt_folder.equals("Multiple File Folder_dummy"));
	System.out.println("Pass-> Folder name as " +txt_folder+ " created successfully!!!");
}

@When("^Click on Select Link or Folder name to Upload Multiple File$")
public void click_on_Select_Link_or_Folder_name_to_Upload_Multiple_File() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Multiple File Folder_dummy']")).click();
	chrome.findElement(By.xpath("//button[text()='Upload']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//a[text()='Multiple File']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@name='name']")).sendKeys("Gnana");
	Thread.sleep(1000);
	chrome.findElement(By.xpath("//input[@name='title']")).sendKeys("Multiple File");
	Thread.sleep(1000);
	// find the input element
	WebElement elem = chrome.findElement(By.xpath("//input[@type='file']"));
	Thread.sleep(3000);
	// 'type' the file location to it as it were a usual <input type='text' /> element
	elem.sendKeys("C:\\Gnana_Praveen\\Employee.pdf");
	chrome.findElement(By.xpath("//input[@value='Submit']")).click();
	//Verify alert exist
	if(isAlertExist_multiple_file(chrome)==true) {
		System.out.println("Multiple_File_Alert: Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("_File_Alert: No Alert was found");
	}
	
	}
static boolean isAlertExist_multiple_file(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}

@Then("^Verify Multiple file Upload is Success$")
public void verify_Multiple_file_Upload_is_Success() throws Throwable {
	String single_file_validate=chrome.findElement(By.xpath("//span[contains(text(),' files uploaded successfully')]")).getText();
	Assert.assertTrue("Fail: Multiple  file not uploaded",single_file_validate.contains("files uploaded successfully"));
	System.out.println("Pass: Multiple file upoaded successfuly");
}
	

//start of zip file upload
@When("^Click on Create Folder Link to create an folder with name Zip File Folder$")
public void click_on_Create_Folder_Link_to_create_an_folder_with_name_Zip_File_Folder() throws Throwable {
	chrome.findElement(By.xpath("//button[text()='Create Folder']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@name='assignment_name']")).sendKeys("Zip File Upload");
	Thread.sleep(2000);
	WebElement dateBox=chrome.findElement(By.xpath("//input[@name='end_date']"));
    dateBox.sendKeys("29052020");
    Thread.sleep(2000);
    dateBox.sendKeys(Keys.TAB);
    Thread.sleep(2000);
    
    //**Options selection**/
    chrome.findElement(By.xpath("//td[contains(text(),'Reference')]/ancestor::tr/descendant::input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'Quotes')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'small')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'Option')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@value='submit']")).click();
   Thread.sleep(2000);
   //**Alert msg for folder creation**/
	if(isAlertPresent_folderCreate_3(chrome))
	{
		System.out.println("Folder Creation-> Alert exist");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Folder Creation-> Alert doesnot exist");
	}
}
static boolean isAlertPresent_folderCreate_3(WebDriver chrome)
{
	try {
		chrome.switchTo().alert();
		return true;
	}catch(NoAlertPresentException e)
	{
		return false;
	}
}

@Then("^Verify Folder Creation is success with name Zip File Folder$")
public void verify_Folder_Creation_is_success_with_name_Zip_File_Folder() throws Throwable {
	String txt_folder=chrome.findElement(By.xpath("//span[text()='Zip File Upload']")).getText();
	Assert.assertTrue("Fail: Folder not created", txt_folder.equals("Zip File Upload"));
	System.out.println("Pass-> Folder name as " +txt_folder+ " created successfully!!!");
}

@When("^Click on Select Link or Folder name to Upload Zip File$")
public void click_on_Select_Link_or_Folder_name_to_Upload_Zip_File() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Zip File Folder']")).click();
	chrome.findElement(By.xpath("//button[text()='Upload']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("(//i[@class='fa'])[2]")).click();
	Thread.sleep(2000);
	// find the input element
	WebElement elem = chrome.findElement(By.xpath("//input[@type='file']"));
	Thread.sleep(2000);
	// 'type' the file location to it as it were a usual <input type='text' /> element
	elem.sendKeys("C:\\Gnana_Praveen\\Desktop.zip");
	Thread.sleep(10000);
	chrome.findElement(By.xpath("//button[text()='Upload']")).click();
	Thread.sleep(2000);
	
	//Verify alert exist
	if(isAlertExist_zip_file(chrome)==true) {
		System.out.println("Zip_File_Alert: Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Zip_File_Alert: No Alert was found");
	}
	chrome.findElement(By.xpath("//input[@type='submit']")).click();
	Thread.sleep(2000);
	}
static boolean isAlertExist_zip_file(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}



@Then("^Verify Zip file Upload is Success$")
public void verify_Zip_file_Upload_is_Success() throws Throwable {
		String single_file_validate=chrome.findElement(By.xpath("//span[contains(text(),'Zip File uploaded')]")).getText();
	Assert.assertTrue("Fail:Zip file not uploaded",single_file_validate.contains("Zip File uploaded"));
	System.out.println("Pass: Zip file upoaded successfuly");
	
}
//end of zip file upload

	//Instructor account Search and delete functionality validation
@When("^then validate Search Field by navigating back to Home Page$")
public void then_validate_Search_Field_by_navigating_back_to_Home_Page() throws Throwable {
	chrome.findElement(By.xpath("//input[@name='search_term']")).sendKeys("Single");
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//button[text()='Search']")).click();
	Thread.sleep(2000);
	//chrome.findElement(By.xpath("//button[text()='Home']")).click();
	//Thread.sleep(1000);
	String Search_filed=chrome.findElement(By.xpath("//p[contains(text(),'Search Results')]")).getText();
	Assert.assertTrue("Fail Message-> search field is not working",
			Search_filed.contains("Search Results"));
	System.out.println("Pass Message-> Action Success! Search Functionality is working !!!!");
	
}

@When("^to validate delete functionality create an dummy folder$")
public void to_validate_delete_functionality_create_an_dummy_folder() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Home > ']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//button[text()='Create Folder']")).click();
    Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@name='assignment_name']")).sendKeys("Dummy File Folder");
	Thread.sleep(2000);
	WebElement dateBox=chrome.findElement(By.xpath("//input[@name='end_date']"));
    dateBox.sendKeys("29052020");
    Thread.sleep(2000);
    dateBox.sendKeys(Keys.TAB);
    Thread.sleep(2000);
    
    //**Options selection**/
    chrome.findElement(By.xpath("//td[contains(text(),'Reference')]/ancestor::tr/descendant::input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'Quotes')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'small')]/ancestor::tr/td/label/input[@value='no']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//td[contains(text(),'Option')]/ancestor::tr/td/label/input[@value='yes']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@value='submit']")).click();
   Thread.sleep(2000);
   //**Alert msg for folder creation**/
	if(isAlertPresent_dummy(chrome))
	{
		System.out.println("Dummy Folder Creation-> Alert exist");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Dummy Folder Creation-> Alert doesnot exist");
	}
	

}
static boolean isAlertPresent_dummy(WebDriver chrome)
{
	try {
		chrome.switchTo().alert();
		return true;
	}catch(NoAlertPresentException e)
	{
		return false;
	}
}

@Then("^Click on Delete Link$")
public void click_on_Delete_Link() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Dummy File Folder']/parent::a/parent::td/following-sibling::td[4]/div/button[@type='button']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//span[text()='Dummy File Folder']/parent::a/parent::td/following-sibling::td[4]/div[@class='dropdown open']/div/a[text()=' Delete ']")).click();
	Thread.sleep(2000);
	//**Alert for delete**/
	if(isAlertPresent_del(chrome))
		{
			System.out.println("Delelte Folder-> Alert exist");
			chrome.switchTo().alert().accept();
		}else {
			System.out.println("Delete Folder-> Alert doesnot exist");
		}
	String Delete_folder=chrome.findElement(By.xpath("//span[text()='Folder deleted successfully.']")).getText();
	Assert.assertTrue("Fail Message->Unable to delete folder",
			Delete_folder.equals("Folder deleted successfully"));
	System.out.println("Pass Message->Folder deleted successfully");
	}
static boolean isAlertPresent_del(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}

@Then("^Click on Account info to view user deatils$")
public void click_on_Account_info_to_view_user_deatils() throws Throwable {
 chrome.findElement(By.xpath("//span[text()='Gnana S']")).click();
 Thread.sleep(3000);
 chrome.findElement(By.xpath("//a[text()='Account Information']")).click();
 Thread.sleep(2000);
 
}

@Then("^Click on Recently Deleted Link to view deleted documents$")
public void click_on_Recently_Deleted_Link_to_view_deleted_documents() throws Throwable {
   chrome.findElement(By.xpath("//a[@href='/drillbit_new/submissionsRef/recycleBin']")).click();
   Thread.sleep(3000);
   //to restore
   chrome.findElement(By.xpath("//span[contains(text(),'restore in')]")).click();
   //alert
   if(isAlertExist_Restore_file(chrome)==true) {
		System.out.println("Restore File->  Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Restore File->  No Alert was found");
	}
   String restore=chrome.findElement(By.xpath("//span[contains(text(),'Restored')]")).getText();
   Assert.assertTrue("Fail Message-> Could not Restore Deleted File", restore.contains("Restored"));
   System.out.println("Pass Message-> Deleted File Successfully Restored to folder");
	
	}
static boolean isAlertExist_Restore_file(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}

@Then("^also Click on View Repository to see the files saved$")
public void also_Click_on_View_Repository_to_see_the_files_saved() throws Throwable {
    chrome.findElement(By.xpath("//span[contains(text(),'User Information')]")).click();
    Thread.sleep(3000);
    chrome.findElement(By.xpath("//i[contains(text(),'View')]")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//span[contains(text(),'User Information')]")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@value='Go Back']")).click();
    Thread.sleep(2000);
}

@Then("^Click on Change Password Link to validate that functionality$")
public void click_on_Change_Password_Link_to_validate_that_functionality() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Gnana S']")).click();
	 Thread.sleep(3000);
	 chrome.findElement(By.xpath("//a[text()='Change Password']")).click();
	 Thread.sleep(2000);
	 chrome.findElement(By.xpath("//input[@name='expass']")).sendKeys("12345678");
	 Thread.sleep(2000);
	 chrome.findElement(By.xpath("//input[@name='password1']")).sendKeys("12345678");
	 Thread.sleep(2000);
	 chrome.findElement(By.xpath("//input[@name='password2']")).sendKeys("12345678");
	 Thread.sleep(2000);
	 chrome.findElement(By.xpath("(//input[@type='submit'])[1]")).click();
	 Thread.sleep(5000);
}

@When("^then Click on Add More Files to upload$")
public void then_Click_on_Add_More_Files_to_upload() throws Throwable {
	//chrome.findElement(By.xpath("//span[text()='Multiple File Folder']")).click();
	chrome.findElement(By.xpath("//button[text()='Upload']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("//a[text()='Multiple File']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys("AMF 1");
	Thread.sleep(1000);
	chrome.findElement(By.xpath("(//input[@name='title'])[1]")).sendKeys("Add More File 1");
	Thread.sleep(1000);
	// find the input element
	WebElement elem = chrome.findElement(By.xpath("(//input[@type='file'])[1]"));
	Thread.sleep(3000);
	// 'type' the file location to it as it were a usual <input type='text' /> element
	elem.sendKeys("C:\\Gnana_Praveen\\Employee.pdf");
	//add file 2
	chrome.findElement(By.xpath("//input[@id='addFile']")).click();
	Thread.sleep(2000);
	chrome.findElement(By.xpath("(//input[@name='name'])[2]")).sendKeys("AMF 2");
	Thread.sleep(1000);
	chrome.findElement(By.xpath("(//input[@name='title'])[2]")).sendKeys("Add More File 2");
	Thread.sleep(1000);
	// find the input element
	WebElement elem1 = chrome.findElement(By.xpath("(//input[@type='file'])[2]"));
	Thread.sleep(3000);
	// 'type' the file location to it as it were a usual <input type='text' /> element
	elem1.sendKeys("C:\\DRILLBIT_DOC\\Mahatma Gandhi.docx");
	chrome.findElement(By.xpath("//input[@id='addFile']")).click();
	Thread.sleep(2000);
	//file 2
	chrome.findElement(By.xpath("(//input[@name='name'])[3]")).sendKeys("AMF 3");
	Thread.sleep(1000);
	chrome.findElement(By.xpath("(//input[@name='title'])[3]")).sendKeys("Add More File 3");
	Thread.sleep(1000);
	// find the input element
	WebElement elem2 = chrome.findElement(By.xpath("(//input[@type='file'])[3]"));
	Thread.sleep(3000);
	// 'type' the file location to it as it were a usual <input type='text' /> element
	elem2.sendKeys("C:\\Gnana_Praveen\\A freedom fighter.doc");
	//chrome.findElement(By.xpath("//input[@id='addFile']")).click();
	//Thread.sleep(2000);
	chrome.findElement(By.xpath("//input[@value='Submit']")).click();
	//Verify alert exist
	if(isAlertExist_add_more_file(chrome)==true) {
		System.out.println("Add More File-> Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Add More File-> No Alert was found");
	}
	
	}
static boolean isAlertExist_add_more_file(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}

@When("^Click on Download Folder Report in Instructor Acc$")
public void click_on_Download_Folder_Report_in_Instructor_Acc() throws Throwable {
 chrome.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
 Thread.sleep(2000);
 if(isAlertExist_download(chrome)==true) {
		System.out.println("Download Folder Report in Instru Acc-> Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Download Folder Report in Instru Acc-> No Alert was found");
	}
	
	}
static boolean isAlertExist_download(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}

@Then("^Validate Search Filed by Author Name$")
public void validate_Search_Filed_by_Author_Name() throws Throwable {
  chrome.findElement(By.xpath("//input[@name='search_term']")).sendKeys("AMF"); 
  Thread.sleep(2000);
  chrome.findElement(By.xpath("//button[text()='Search']")).click();  
}

@Then("^validate Logout Link in Instructor Acc$")
public void validate_Logout_Link_in_Instructor_Acc() throws Throwable {
	chrome.findElement(By.xpath("//span[text()='Gnana S']")).click();
	 Thread.sleep(3000);
	 chrome.findElement(By.xpath("//a[text()='Logout']")).click();
	 Thread.sleep(2000);
}

@Then("^Clik on folder name or Select link to navigate to submission page$")
public void clik_on_folder_name_or_Select_link_to_navigate_to_submission_page() throws Throwable {

    Thread.sleep(2000);
}

@Then("^Click on Download link to view result$")
public void click_on_Download_link_to_view_result() throws Throwable {
   chrome.findElement(By.xpath("//td[text()='Gnana']/ancestor::tr/td[9]/span/a")).click();
   Thread.sleep(2000);
   if(isAlertExist_download_result(chrome)==true) {
		System.out.println("Download Report in Submission Page-> Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Download Report in Submission Page-> No Alert was found");
	}
	
	}
static boolean isAlertExist_download_result(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}


@Then("^Click on Delete button to delete any file or document$")
public void click_on_Delete_button_to_delete_any_file_or_document() throws Throwable {
   chrome.findElement(By.xpath("//td[text()='AMF 2']/ancestor::tr/td[9]/a")).click();
   if(isAlertExist_delete(chrome)==true) {
		System.out.println("Delete Report in Submission Page-> Alert Exist...");
		chrome.switchTo().alert().accept();
	}else {
		System.out.println("Delete Report in Submission Page-> No Alert was found");
	}
	
}
static boolean isAlertExist_delete(WebDriver chrome)
	{
		try {
			chrome.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
}


@Then("^Click on Similiarty percenatge to view in deatils$")
public void click_on_Similiarty_percenatge_to_view_in_deatils() throws Throwable {
	String Parent_window=chrome.getWindowHandle();
    chrome.findElement(By.xpath("//td[text()='AMF 3']/ancestor::tr/td[8]")).click();
    Thread.sleep(2000);
    
    
}

@Then("^again Click on Download Tab in result sheet to view$")
public void again_Click_on_Download_Tab_in_result_sheet_to_view() throws Throwable {
    //chrome.findElement(By.xpath("//a[@id='downloadFile']")).click();
    Thread.sleep(4000);
    WebDriverWait waitr = new WebDriverWait(chrome, 2000);
    WebElement element = waitr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='downloadFile']")));
    element.click();
}

@Then("^Click on Save to Repository less than (\\d+) percenatge$")
public void click_on_Save_to_Repository_less_than_percenatge(int arg1) throws Throwable {
	//chrome.switchTo().frame(0);
	//chrome.switchTo().frame("iframe2");
	//chrome.switchTo().frame("some_id");
	//WebElement frame = chrome.findElement(By.xpath("/html/body/div[2]/div[5]/ul/li[3]/a"));
	 //WebElement we1 = chrome.findElement(By.xpath("//a[@href='/drillbit_new/submissionsRef/saveToRepository?paper_id=103504']/i "));
	 //Actions action=new Actions(chrome);
	 //action.moveToElement(we1).perform();
	 //we1.click();
	Set<String> child_windows=chrome.getWindowHandles();
	for(String child_w:child_windows) {
		chrome.switchTo().window(child_w);
		chrome.findElement(By.xpath("//a[@href='/drillbit_new/submissionsRef/saveToRepository?paper_id=103504']/p")).click();
	    Thread.sleep(2000);
	}	
    chrome.findElement(By.xpath("//input[@name='name']")).sendKeys("Repo Admin 1");
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@name='title']")).sendKeys("Repo Paper 1");
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@value='Submit']")).click();
    Thread.sleep(2000);
    if(isAlertExist_save_to_repo(chrome)==true) {
 		System.out.println("Delete Report in Submission Page-> Alert Exist...");
 		chrome.switchTo().alert().accept();
 	}else {
 		System.out.println("Delete Report in Submission Page-> No Alert was found");
 	}
 	
 }
 static boolean isAlertExist_save_to_repo(WebDriver chrome)
 	{
 		try {
 			chrome.switchTo().alert();
 			return true;
 		}catch(NoAlertPresentException e)
 		{
 			return false;
 		}
 }
@Then("^Clcik on Email Notification tab$")
public void clcik_on_Email_Notification_tab() throws Throwable {
	chrome.findElement(By.xpath("//td[text()='AMF 3']/ancestor::tr/td[8]")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//p[text()='Email Notification']")).click();
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@name='email']")).sendKeys("gnanass1995@gmail.com");
    Thread.sleep(2000);
    chrome.findElement(By.xpath("//input[@value='Submit']")).click();  
    if(isAlertExist_email(chrome)==true) {
 		System.out.println("Delete Report in Submission Page-> Alert Exist...");
 		chrome.switchTo().alert().accept();
 	}else {
 		System.out.println("Delete Report in Submission Page-> No Alert was found");
 	}
 	
 }
 static boolean isAlertExist_email(WebDriver chrome)
 	{
 		try {
 			chrome.switchTo().alert();
 			return true;
 		}catch(NoAlertPresentException e)
 		{
 			return false;
 		}
 }

@Then("^Click on Exclude or Include Data$")
public void click_on_Exclude_or_Include_Data() throws Throwable {
   chrome.findElement(By.xpath("//input[@value='Back']")).click();
   Thread.sleep(2000);
   chrome.findElement(By.xpath("//i[@class='fa fa-caret-down fa-1x']")).click();
   Thread.sleep(2000);
   chrome.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
   Thread.sleep(1000);
   chrome.findElement(By.xpath("(//input[@value='Exclude'])[1]")).click();
   if(isAlertExist_exclude_or_include(chrome)==true) {
 		System.out.println("Delete Report in Submission Page-> Alert Exist...");
 		chrome.switchTo().alert().accept();
 	}else {
 		System.out.println("Delete Report in Submission Page-> No Alert was found");
 	}
 	
 }
 static boolean isAlertExist_exclude_or_include(WebDriver chrome)
 	{
 		try {
 			chrome.switchTo().alert();
 			return true;
 		}catch(NoAlertPresentException e)
 		{
 			return false;
 		}
 }
   


@Then("^Click on Exit$")
public void click_on_Exit() throws Throwable {
    chrome.findElement(By.xpath("////p[text()='Exit']")).click();
    }



	



	
	
	
	
	
}

	    
	

	    	
	



