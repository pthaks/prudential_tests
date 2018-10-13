package prudential.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import prudential.base.Reusables;

public class Tests extends Reusables{
	
	@BeforeTest
	public void beforeTest()
	{
		initiateDriver();
	}
	
	//SCENARIOS # 1
	/*
	1.       Starts at httpsopenweathermap.org
	2.       Verifies that all important information is there, e.g.  labels etc. (Give it a thought for what is important to test)
	*/
	@Test(enabled=true, priority=1)
	public void verifySite()
	{
		System.out.println("Scenario 1 Starts ----------------------");
		SoftAssert softAssert = new SoftAssert();
		try
		{
			
			getPageTitle();
			
			softAssert.assertEquals(verifyUnitChecksExist(), true);
			
			softAssert.assertEquals(verifyHeaderExists(), true);
			
			softAssert.assertEquals(verifyCitySearchExists(), true);
			
			softAssert.assertAll();
			
			/*
			 * Can also check following, but have not added -
			 * Page header/footer
			 * Links
			 * sign in/sign up links
			 * If current location access is allowed, verify current location weather gets displayed
			 * */
		}
		catch(Exception e)
		{
			System.out.println("Exception in TEST verifySite() --> " + e.getMessage());
			//e.printStackTrace();
			assertTrue(false); //Indicating that test case failed
		}
		System.out.println("Scenario 1 Ends ----------------------\n");
	}
	
	//SCENARIO # 2
	/* Invalid city search scenario
	1.       Starts on the httpsopenweathermap.org
	2.       Enters an invalid city name
	3.       Searches for the weather
	4.       Verifies that website suggests city is Not found
	*/
	@Test(enabled=true, priority=2)
	public void invalidCities()
	{
		System.out.println("Scenario 2 Starts ----------------------");
		try
		{
			setCityName("abcde");
			System.out.println("2.Enters an invalid city name");
			
			clickSearch();
			
			String result = notFoundResult();
			System.out.println("City name -- abcde is " + result );
			Assert.assertEquals(result, "Not found");
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in TEST invalidCities() --> " + e.getMessage());
			//e.printStackTrace();
			assertTrue(false); //Indicating that test case failed
		}
		System.out.println("Scenario 2 Ends ----------------------\n");
	}
	
	//Scenario 3 
	/*
	3.       Write a last end-to-end test that
			1.       Starts on the httpsopenweathermap.org
			2.       Enters a valid city name
			3.       Searches for the weather
			4.       Verifies that website successfully returns weather details for the city.
	 */
	@Test (dataProvider="getCityNames", enabled=true, priority=3)
	public void validCities(String city, String cityName)
	{
		System.out.println("Scenario 3 Starts ----------------------");
		try
		{
			setCityName(cityName);
			System.out.println("2. Enters a valid city name");
			
			clickSearch();
		
			System.out.println("Result of " + cityName + "*******************");
			String data = getCityResult();
			System.out.println(data);
			System.out.println("End of result *******************************\n");
			
			Assert.assertEquals((data.length()!=0), true);
		}
		catch(Exception e)
		{
			System.out.println("Exception in TEST validCities() --> " + e.getMessage());
			//e.printStackTrace();
			assertTrue(false); //Indicating that test case failed
		}
		System.out.println("Scenario 3 Ends ----------------------\n");
	}
	
	//Scenario 4
	/*
	 ** 4.       Write at least one additional test which you feel is important.
	 *Adding case - If user enters invalid inputs like single chars, numbers, special numbers
	 */
	@Test (dataProvider="invalidcityinputs", enabled=true, priority=4)
	public void invalidCityInputs(String city, String cityName)
	{
		System.out.println("Scenario 4 Starts ----------------------");
		try
		{
			setCityName(cityName);
			System.out.println("2. Enters Invalid city input");
			
			clickSearch();
		
			System.out.println("Result of " + cityName + "*******************");
			String data = getCityResult();
			System.out.println(data);
			System.out.println("End of result *******************************\n");
			
			Assert.assertEquals(data, "Not valid city input", "Invalid city data inputs");
		}
		catch(Exception e)
		{
			System.out.println("Exception in TEST invalidCityInputs() --> " + e.getMessage());
			//e.printStackTrace();
			assertTrue(false); //Indicating that test case failed
		}
		System.out.println("Scenario 4 Ends ----------------------\n");
	}
	
	@DataProvider(name = "getCityNames")
	public static Object[][] getCityNames() 
	{
		//valid city input data
		return new Object[][] 
		{ 
			{ "city", "London, GB" }, 
			{ "city", "agra, IN" },
			{ "city", "Goa" },
		};
	}
	
	@DataProvider(name = "invalidcityinputs")
	public static Object[][] getCityInputs() 
	{
		//invalid city input data
		return new Object[][] 
		{ 
			{ "city", "a" }, 
			{ "city", "c123" },
			{ "city", "" },
			{ "city", "%$#%$#%$#%" },
			{ "city", "3" },
		};
	}
	
	@AfterTest
	public void cleanUp()
	{
		closeDriver();
	}

}
