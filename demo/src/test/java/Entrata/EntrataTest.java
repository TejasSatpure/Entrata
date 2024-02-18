package Entrata;


	

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EntrataTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\demo\\src\\test\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testEntrataWebsite() {
        driver.get("https://www.entrata.com/");
		
		// Verify the page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Property Management Software | Entrata");


        // Click on the "Watch demo" button
        WebElement watchDemoButton = driver.findElement(By.xpath("//a[contains(text(), 'Watch Demo')]"));
        watchDemoButton.click();

        // Wait for the demo page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("demo"));

        // Fill in the form fields
        driver.findElement(By.id("FirstName")).sendKeys("Tejas");
        driver.findElement(By.id("LastName")).sendKeys("Satpure");
        driver.findElement(By.id("Email")).sendKeys("tsatpure@example.com");
        driver.findElement(By.id("Company")).sendKeys("Example Company");
        driver.findElement(By.id("Phone")).sendKeys("1234567890");
        driver.findElement(By.id("Unit_Count__c")).sendKeys("100");
        driver.findElement(By.id("Title")).sendKeys("Software Tester");

        // Verify that the "Watch demo" button is clickable
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Watch Demo')]"));
        Assert.assertTrue(submitButton.isEnabled(), "Watch Demo button is not clickable");

        
		 // Go back to the home page
        driver.navigate().back();

        // Wait for the sign-in button to display
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Sign In')]")));
        signInButton.click();

        // Wait for the page to load
        wait.until(ExpectedConditions.urlContains("sign-in"));

        // Verify the presence of the specified text
        WebElement pageText = driver.findElement(By.tagName("body"));
        String text = pageText.getText();
        Assert.assertTrue(text.contains("Entrata powers residents and property managers"), "Text not found on the page");
        Assert.assertTrue(text.contains("If you are a property manager, login with your property details. If you are a resident, please use the link below to access ResidentPortal."), "Text not found on the page");
    
	    // Click on the "Property Manager Login" button
        WebElement propertyManagerLoginButton = driver.findElement(By.xpath("//a[@title='Client Login Button']"));
      wait.until(ExpectedConditions.elementToBeClickable(propertyManagerLoginButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
        		propertyManagerLoginButton);
//        wait.until(ExpectedConditions.elementToBeClickable(propertyManagerLoginButton));

//        propertyManagerLoginButton.click();

        // Verify the URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://sso.entrata.com/entrata/login");

        // Fill in the username and password fields with invalid credentials
        driver.findElement(By.xpath("//input[@id='entrata-username']")).sendKeys("invalid_username");
        driver.findElement(By.xpath("//input[@id='entrata-password']")).sendKeys("invalid_password");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify the error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[@id='statusMessage']"));
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, "The username and password provided are not valid. Please try again.", "Error message not matching");

        
		// Go back to the home page
        driver.navigate().to("https://www.entrata.com/");
      
	

        // Scroll down to the "Take a tour" button
        WebElement takeATourButton = driver.findElement(By.xpath("//a[@title='Take the tour (demo button)']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", takeATourButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
        		takeATourButton);
        // Verify the redirection to the "Watch Demo" page
        wait.until(ExpectedConditions.urlContains("demo"));
        Assert.assertTrue(driver.getCurrentUrl().contains("demo"), "Page is not redirected to Watch Demo page");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

	
	
	
	


