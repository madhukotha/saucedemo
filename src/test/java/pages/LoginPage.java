package pages;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import common.ParallelTest;
import common.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
	
	WebDriver driver;
	ParallelTest test;
	
	@Test
	public void chromeTest() throws IOException
	{
		test=new ParallelTest(driver);
		test.selectBrowser("chrome");
		test.run();
	}
	
	@Test
	public void firefoxTest() throws IOException
	{
		test=new ParallelTest(driver);
		test.selectBrowser("firefox");
		test.run();
	}
	
	@Test(dataProvider = "readData",dataProviderClass = ReadExcel.class)
	public void dataProvider(String userName, String password) throws Exception
	{	
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		String actual="https://www.saucedemo.com/inventory.html";
		String expected=driver.getCurrentUrl();
		assertEquals(actual, expected);
		Thread.sleep(1000);
		driver.quit();
	}
	
}
