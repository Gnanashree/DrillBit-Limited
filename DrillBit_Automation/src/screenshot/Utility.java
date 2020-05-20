package screenshot;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Utility {
	public static void captureScreenshot(WebDriver wb,String screenshot)
	{
		try
		{
			TakesScreenshot ts=(TakesScreenshot)wb;
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source,new File("./Screenshots/"+screenshot+".png"));
			System.out.println("Screen shot taken");
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
