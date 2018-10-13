To run this suite -
1. You need to have Java JDK, eclipse installed.
2. This is designed with Java and TESTNG as the basic framework.
3. Download the repo from the git path.
4. Import the project in eclipse.
5. You need to update build path to include all the jar files from lib folder and user library as TESTNG
4. Tests are added as per the scenarios in Tests.java -
	a. scenario 1- Verify important info on the site
	b. scenario 2 - invalidCities
	c. scenario 3 - validCities
	d. scenario 4 - invalid city names like special chars, a, b, c etc.
5. Right Click on Runner.xml -> Run as TestNg suite.
6. This should be run chromedriver.
7. Have attached the console outout (consoleoutput.txt) for your perusable.


Items I couldnt complete -
1. Ant - I have added ant.build file which should have worked with jenkins if configured properly. I havent completed the config part.
2. In ideal case, notFoundResult() and getCityResult() should have been combined, but for readability purpose have kept it separate.
3. Compatibility of scripts wrt to different browsers.
