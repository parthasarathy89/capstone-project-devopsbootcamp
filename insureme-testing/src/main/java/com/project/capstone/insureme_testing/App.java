package com.project.capstone.insureme_testing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	//System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
    	System.out.println("Starting Test case Execution");
    	ChromeOptions chromeOptions = new ChromeOptions();
    	//chromeOptions.setCapability("browserVersion", "119.0.6045.105");
    	chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setBinary("/usr/bin/google-chrome");
    	System.out.println("After browset settings");
    	WebDriver webDriver = new ChromeDriver(chromeOptions);
    	System.out.println("Starting Test case Execution");
    	System.out.println("Passing Input details");
    	// Open InsureMe Contact URL
        webDriver.get("http://54.88.130.118:8081/contact.html");
        // Pass Name
        webDriver.findElement(By.id("inputName")).sendKeys("Partha Sarathy");
        // Pass Phone Number
        webDriver.findElement(By.id("inputNumber")).sendKeys("9999999999");
        // Pass Email
        webDriver.findElement(By.id("inputMail")).sendKeys("test@dummy.com");
        // Pass Phone Number
        webDriver.findElement(By.id("inputMessage")).sendKeys("Please call me to discuss");
        // Click Login
        webDriver.findElement(By.id("my-button")).click();
        System.out.println("Getting webpage response");
        // Check webpage response
        String r=webDriver.findElement(By.id("response")).getText();        
        System.out.println(r);
        // Validate webpage response
        if (r.equals("Message Sen")) {
        	System.out.println("Webpage response successfully validated");
        	webDriver.close();
        	System.exit(0);	
        }
        else {
        	System.out.println("Error validating webpage response");
        	webDriver.close();
        	System.exit(1);
        }
                
    }
}
