package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

import java.time.Duration;

public class FeedbackFormTest {

    @Test
    public void testFeedbackForm() {

        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open local HTML from project
        String projectPath = System.getProperty("user.dir");
        driver.get("file:///" + projectPath + "/feedback.html");

        // Fill form
        driver.findElement(By.id("studentName")).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("mobile")).sendKeys("9876543210");

        Select dept = new Select(driver.findElement(By.id("department")));
        dept.selectByVisibleText("Computer Science");

        driver.findElement(By.id("male")).click();

        driver.findElement(By.id("comments"))
              .sendKeys("This is a valid feedback comment containing more than ten words.");

        // Submit
        driver.findElement(By.id("submitBtn")).click();

        // Wait for success message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successMessage"))
        ).getText();

        if (success.toLowerCase().contains("feedback submitted successfully")) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        driver.quit();
    }
}