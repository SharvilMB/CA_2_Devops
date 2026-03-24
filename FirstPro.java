import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FirstPro {
    WebDriver driver;
    // Replace this with the actual path to your HTML file
    String url = "file:///C://sem_6//devops//CA2_Feedback//index.html"; 

    @Before
    public void setUp() {
        // Ensure you have ChromeDriver installed or use WebDriverManager
        System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testPageOpensSuccessfully() {
        driver.get(url);
        assertEquals("Student Feedback Registration", driver.getTitle());
    }

    @Test
    public void testValidDataSubmission() {
        driver.get(url);
        driver.findElement(By.id("studentName")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("mobile")).sendKeys("1234567890");
        driver.findElement(By.id("department")).sendKeys("Computer Science");
        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.id("comments")).sendKeys("This is a great course and I learned a lot of things.");
        
        driver.findElement(By.id("submitBtn")).click();
        
        // Handle the JS Alert on success
        String alertText = driver.switchTo().alert().getText();
        assertEquals("Form submitted successfully!", alertText);
        driver.switchTo().alert().accept();
    }

    @Test
    public void testMandatoryFieldsBlank() {
        driver.get(url);
        driver.findElement(By.id("submitBtn")).click();
        assertTrue(driver.findElement(By.id("nameError")).isDisplayed());
    }

    @Test
    public void testInvalidEmailFormat() {
        driver.get(url);
        driver.findElement(By.id("email")).sendKeys("invalid-email");
        driver.findElement(By.id("submitBtn")).click();
        assertTrue(driver.findElement(By.id("emailError")).isDisplayed());
    }

    @Test
    public void testInvalidMobileNumber() {
        driver.get(url);
        driver.findElement(By.id("mobile")).sendKeys("123xyz");
        driver.findElement(By.id("submitBtn")).click();
        assertTrue(driver.findElement(By.id("mobileError")).isDisplayed());
    }

    @Test
    public void testResetButton() {
        driver.get(url);
        WebElement nameInput = driver.findElement(By.id("studentName"));
        nameInput.sendKeys("John Doe");
        driver.findElement(By.id("resetBtn")).click();
        assertEquals("", nameInput.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}