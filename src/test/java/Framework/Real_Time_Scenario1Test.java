package Framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Real_Time_Scenario1Test extends DriverClass{
	WebDriver driver;	
	 public static Logger log =LogManager.getLogger(Real_Time_Scenario1Test.class.getName());
	
	 @Parameters("browser")
	@Test
	public void Scenario1(String browser) throws Exception {
		
		
	
		// TODO Auto-generated method stub
		driver=initializeDriver(browser);
	    
		log.info("Driver is initialized");

		String URLName=getURL("Real_Time_Scenario1TestURL");
		driver.get(URLName);
		
		System.out.println("The title of the page is "+driver.getTitle());
		System.out.println("The Current URL of the page is "+driver.getCurrentUrl());
		driver.navigate().to("https://www.facebook.com");
		driver.navigate().back();
		System.out.println("The Current URL of the page is "+driver.getCurrentUrl());
		driver.navigate().forward();
		driver.navigate().refresh();
		
		log.info("Successfully validated Text message");

		driver.quit();
		
	}}
		


