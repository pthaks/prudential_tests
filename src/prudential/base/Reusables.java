package prudential.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class Reusables {
	
	private String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe"; 
	private WebDriver driver=null;
	private WebDriverWait wait=null;
	
	public void initiateDriver()
	{
		try
		{
			System.setProperty("webdriver.chrome.driver", chromePath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("enable-strict-powerful-feature-restrictions");
			options.addArguments("disable-geolocation");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			driver.get("https://openweathermap.org/");
			System.out.println("1. Starts on the httpsopenweathermap.org");
		}
		catch(Exception e)
		{
			System.out.println("Exception in initiateDriver() --> " + e.getMessage());
		}
	}
	
	public void getPageTitle()
	{
		System.out.println("Page title --> "+ driver.getTitle());
	}
	
	public boolean verifyHeaderExists()
	{
		try
		{
			if (driver.findElements(By.xpath("//h2[contains(@class,'widget__title')]")).size()!=0)
			{
				System.out.println("Header title --> " + driver.findElement(By.xpath("//h2[contains(@class,'widget__title')]")).getText());
				return true;
			}
			else
			{
				System.out.println("Page header title - Not present");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in verifyHeaderExists() --> " + e.getMessage());
			return false;
		}
	}
	
	public boolean verifyCitySearchExists()
	{
		try
		{
			if (driver.findElements(By.name("q")).size()!=0)
			{
				System.out.println("City search box is present");
				return true;
			}
			else
			{
				System.out.println("City search box - Not present");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in verifyCitySearchExists() --> " + e.getMessage());
			return false;
		}
	}
	
	public boolean verifyUnitChecksExist()
	{
		try
		{
			if (driver.findElements(By.id("units_check")).size()!=0)
			{
				System.out.println("Unit Checks - Celcius and Farhenite are present");
				return true;
			}
			else
			{
				System.out.println("Unit Checks - Not present");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in verifyUnitChecksExist() --> " + e.getMessage());
			return false;
		}
	}
	
	
	public void setCityName(String expectedCityName)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='q'])[2]")));
			e.clear();
			e.sendKeys(expectedCityName);
		}
		catch (Exception e)
		{
			System.out.println("Exception in setCityName() --> " + e.getMessage());
		}
	}
	
	public void clickSearch()
	{
		try
		{
			driver.findElement(By.xpath("//button[text()=' Search']")).click();
			System.out.println("3.Searches for the weather");
		}
		catch (Exception e)
		{
			System.out.println("Exception in clickSearch() --> " + e.getMessage());
		}
	}
	
	public String notFoundResult()
	{
		String cityResult="";
		try
		{
			if (driver.findElements(By.xpath("//div[@class='alert alert-warning']")).size() !=0)
			{
				System.out.println("4.Verifies that website suggests city is Not found");
				return cityResult="Not found";
			}
			else
			{
				return cityResult ="city might exist";
			}
				
		}
		catch (Exception e)
		{
			System.out.println("Exception in verifyCityResult() --> " + e.getMessage());
			return cityResult;
		}
	}
	
	public String getCityResult()
	{
		String data ="";
		try
		{
			if (driver.findElements(By.xpath("//div[@id='forecast_list_ul']/table")).size() != 0)
			{
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='forecast_list_ul']/table/tbody/tr"));
				for (WebElement row : rows)
				{
					data = data + row.findElement(By.xpath("td[2]")).getText();
				}
			}
			else
			{
				data = "Not valid city input";
			}
			return data;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getCityResult() --> " + e.getMessage());
			//e.printStackTrace();
			return data;
		}
	}
	
	public void closeDriver()
	{
		driver.close();
		driver.quit();
	}
	
}
