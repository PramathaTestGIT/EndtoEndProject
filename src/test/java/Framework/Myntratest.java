package Framework;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class Myntratest extends DriverClass {

	 public static Logger log =LogManager.getLogger(Myntratest.class.getName());
	WebDriver driver;
	WebDriverWait wait;

	@Parameters("browser")
	@Test
	public void NavigationTest(String browser) throws Exception

	{
		driver = initializeDriver(browser);

		String URLName = getURL("MyntratestURL");
		driver.get(URLName);
		
		
		/* code for selecting from women section-->tops-> */

		Actions a = new Actions(driver);
		WebElement moveToWomen = driver.findElement(By.cssSelector("a[data-group='women']"));
		a.moveToElement(moveToWomen).build().perform();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/tops']")));
		
		driver.findElement(By.xpath("//a[@href='/tops']")).click();
		WebElement sortByDropDown = driver.findElement(By.className("sort-sortBy"));
		a.moveToElement(sortByDropDown).click().build().perform();

		/* code for selecting "Whats new" from the dropdown */
        Thread.sleep(2000);
        List<WebElement> allOptionsDropdowns = sortByDropDown.findElements(By.cssSelector("label[class='sort-label ']"));
      
		for (int i = 0; i <= allOptionsDropdowns.size() - 1; i++) {

			if (allOptionsDropdowns.get(i).getText().contains("What's New"))
			{

				a.moveToElement(allOptionsDropdowns.get(i)).click().build().perform();

				break;

			}
		}
		
		 //Thread.sleep(2000);
            
		/* code for selecting Brand,Discount range,Price */

		a.moveToElement(driver.findElement(By.xpath("//ul[@class='brand-list']/li[3]"))).click().build().perform();
     	
		a.moveToElement(driver.findElement(By.xpath("//ul[@class='discount-list']/li[2]"))).click().build().perform();
		
		
		a.moveToElement(driver.findElement(By.xpath("//ul[@class='price-list']/li[2]"))).click().build().perform();
		
		a.moveToElement(driver.findElement(By.xpath("//ul[@class='results-base']/li[5]"))).click().build().perform();

		Set<String> allWindowHandles = driver.getWindowHandles();// 4

		Iterator<String> it = allWindowHandles.iterator();
		
		while (it.hasNext())

		{

			driver.switchTo().window(it.next());
		}

		List<WebElement> Buttonoption = driver.findElements(By.cssSelector(".size-buttons-buttonContainer"));

		for (int j = 1; j <= Buttonoption.size(); j++) {

			if (Buttonoption.get(j).isEnabled()) {
				Buttonoption.get(j).click();

				a.moveToElement(Buttonoption.get(j)).click().build().perform();

				break;

			}
		}

		a.moveToElement(driver.findElement(By.className("pdp-add-to-bag"))).click().build().perform();
		Thread.sleep(1000);

		a.moveToElement(driver.findElement(By.className("desktop-cart"))).click().build().perform();
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("itemComponents-base-toolTipCTA"))));
		Thread.sleep(1000);
		a.moveToElement(driver.findElement(By.className("itemComponents-base-toolTipCTA"))).click().build().perform();
		
		a.moveToElement(driver.findElement(By.xpath("//button[@class='inlinebuttonV2-base-actionButton bulkActionStrip-desktopBulkWishlist']"))).click().build().perform();

		a.moveToElement(driver.findElement(By.cssSelector(".inlinebuttonV2-base-actionButton.bulkActionStrip-waterMelon"))).click().build().perform();

		
		log.info("Successfully validated Myntra websiste");
		driver.quit();

	}
}
