package homework0509;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FirstTC {
    WebDriver driver;

    @BeforeMethod
    public void create() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void selectRandomJobTest() {
        driver.get("https://staff.am/jobs");

        List<WebElement> allJobs = driver.findElements(By.xpath("//a[contains(@href, '/jobs/')]"));
        Random random = new Random();

        int randomIndex = random.nextInt(allJobs.size());
        WebElement randomJob = allJobs.get(randomIndex);
        Actions actions = new Actions(driver);
        actions.scrollToElement(randomJob).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(randomJob));

        randomJob.click();
    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();

        }
    }
}