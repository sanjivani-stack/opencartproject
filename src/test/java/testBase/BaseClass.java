package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public  static WebDriver driver;
	public Logger logger; //for logging
	public ResourceBundle rb;
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) {
		
		rb=ResourceBundle.getBundle("config"); //load config.properties file
		logger=LogManager.getLogger(this.getClass());
		ChromeOptions ops=new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		if(br.equals("chrome"))
		{
			driver=new ChromeDriver(ops);
		}
		/*else
			if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		//WebDriverManager.chromedriver().setup();
		//driver=new ChromeDriver(ops);
		 * 
		 */
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(rb.getString("appURL"));
		
	}
	
	
	@AfterClass
   public void tearDown() {
		driver.quit();
	}
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
	}
	
	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return(generatedString2);
	}
	public String randomeAlphaNumeric()
	{
		String str = RandomStringUtils.randomAlphabetic(5);
		String num = RandomStringUtils.randomNumeric(10);
		return(str+num);
	}
	
	public String captureScreen(String tname) throws IOException {
		
		/*SimpleDateFormat df=new SimpleDateFormat();
		Date dt=new Date();
		String timeStamp=df.format(dt);
		*/

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
}
