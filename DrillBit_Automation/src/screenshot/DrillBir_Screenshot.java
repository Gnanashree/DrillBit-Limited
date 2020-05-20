package screenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class DrillBir_Screenshot {
	WebDriver wb=null;
	@Test
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver wb=new ChromeDriver();
		wb.manage().window().maximize();
		wb.get("http://104.215.191.211:8080/drillbit_new/drillbitlogin");
		//Utility.captureScreenshot(wb, "BrowserStarted");
		wb.findElement(By.xpath("//input[@name='username']")).sendKeys("gnanasss1995@gmail.com");
		Thread.sleep(2000);
		wb.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");
		Thread.sleep(2000);
		wb.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(2000);
		//Utility.captureScreenshot(wb, "TypeUname");

	}
	@AfterMethod
	public void tearDown(ITestResult Result)
	{
		if(ITestResult.FAILURE==Result.getStatus())
			
		{
			Utility.captureScreenshot(wb, Result.getName());
		}
			
	}

}
