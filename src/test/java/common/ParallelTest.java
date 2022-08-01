package common;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ParallelTest {
	
	public WebDriver driver;
	
	public ParallelTest(WebDriver driver) {
		this.driver = driver;
		
	}

	public void selectBrowser(String browser) throws IOException
	{
		FileInputStream fileInputStream = new FileInputStream(globalFilePath());
		Properties property = new Properties();
		property.load(fileInputStream);
		String url = property.getProperty("url");
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public String globalFilePath() {
		return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "global.properties";
	}

	public void run()
	{
		SoftAssert softAssertion= new SoftAssert();
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		String actual="https://www.saucedemo.com/inventory.html";
		String expected=driver.getCurrentUrl();
		softAssertion.assertEquals(actual, expected);
		driver.quit();
		
	}
	
	public void captureScreenshot(String filename) throws IOException
	{	
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ filename);
		FileUtils.copyFile(sourceFile, destFile);
		System.out.println("Screenshot saved successfully");
	}

}
