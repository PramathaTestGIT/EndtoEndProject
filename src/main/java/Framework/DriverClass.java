package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverClass {

	public WebDriver driver;

	@SuppressWarnings("deprecation")
	public WebDriver initializeDriver(String browser) throws Exception {

		{

			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\src\\test\\java\\Drivers\\geckodriver.exe");
				FirefoxProfile profile = new FirefoxProfile();
			    profile.setPreference("permissions.default.desktop-notification", 1);
			    
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(capabilities);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				

			}

			else if (browser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\java\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			}

			else if (browser.equalsIgnoreCase("Edge")) {

				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "\\src\\test\\java\\Drivers\\msedgedriver.exe");
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			}

			else if (browser.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver",System.getProperty("user.dir") + "\\src\\test\\java\\Drivers\\operadriver.exe");
				OperaOptions options = new OperaOptions();
				options.addArguments("--disable-notifications");
				driver = new OperaDriver(options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			}

			else {

				throw new Exception("Incorrect Browser");
			}

			driver.manage().window().maximize();
			

			return driver;

		}
	}

	public String getURL(String URL) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\java\\Framework\\resource\\data.properties");
		prop.load(fis);
		String url = prop.getProperty(URL);
		return url;

	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

}
