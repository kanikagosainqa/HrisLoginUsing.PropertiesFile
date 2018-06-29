package Hris.HrisLogin.PropertiesFile;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class HrisLogin {
	WebDriver driver;
	
	Properties p = new Properties();
	FileInputStream fis;
	String username, password, url;
	
  @BeforeClass
  public void launchBrowser() {
	  System.setProperty("webdriver.chrome.driver", "/home/qainfotech/Desktop/chromedriver");
	  driver = new ChromeDriver();
	  driver.get("https://hris.qainfotech.com/login.php");
	  driver.manage().window().maximize();
	  driver.findElement(By.className("icon-lock")).click();	  
	  try {
		  fis = new FileInputStream("/home/qainfotech/eclipse-workspace/HrisLoginUsingPropertiesFile/HrisLogin/Login.properties");
		  p.load(fis);
		  username = p.getProperty("username","kanikagosain");
		  password = p.getProperty("password", "Kanika@321#");
		  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  
  @Test
  public void loginWithCorrectLoginCredentials() {
	  driver.findElement(By.id("txtUserName")).sendKeys(username);
	  driver.findElement(By.id("txtPassword")).sendKeys(password);
	  driver.findElement(By.id("txtPassword")).submit();
	  Assert.assertEquals("https://hris.qainfotech.com:8086/empFeedback/yourFeedback", driver.getCurrentUrl());
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
