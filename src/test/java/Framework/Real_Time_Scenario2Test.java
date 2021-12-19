package Framework;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Real_Time_Scenario2Test extends DriverClass {

	public static Logger log = LogManager.getLogger(Real_Time_Scenario2Test.class.getName());
	WebDriver driver;

	@Parameters("browser")
	@Test
	public void Scenario2(String browser) throws Exception {

		driver = initializeDriver(browser);
		String URLName = getURL("Real_Time_Scenario2TestURL");
		driver.get(URLName);

		WebElement trendingNowdriver = driver.findElement(By.className("compList"));
		List<WebElement> linksTrendingNows = trendingNowdriver.findElements(By.tagName("a"));
      
		/* code for verifying "trending now" text and printing count of links n opening it*/
		  

		Assert.assertEquals(driver.findElement(By.xpath("//ul/li/div/h4")).getText(), "Trending Now");

		System.out.println("The Total number of links under Trending Now section is " + linksTrendingNows.size());

		for (int i = 0; i < linksTrendingNows.size(); i++) {

			System.out.println(linksTrendingNows.get(i).getText());

			String clickonlinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			linksTrendingNows.get(i).sendKeys(clickonlinkTab);

		} // opens all the tabs

		Set<String> allWindowHandles = driver.getWindowHandles();// 4
		Iterator<String> it = allWindowHandles.iterator();

		String parentId = it.next();

		while (it.hasNext())

		{

			driver.switchTo().window(it.next());
		}

		driver.switchTo().window(parentId);

		
 	//  code for clicking on coronavirus table n scrolling to it n printing headers 
		 

		driver.findElement(By.cssSelector("div[Class='image featured']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");

		List<WebElement> coronaCountTableHead = driver.findElements(By.cssSelector(".dd.coronavirusTable thead tr th"));
		for (int j = 0; j < coronaCountTableHead.size(); j++)

		{
			System.out.println("The header of columns is " + coronaCountTableHead.get(j).getText());

		}
		
		
		log.info("Successfully validated Text message");

		driver.quit();
	}
}
