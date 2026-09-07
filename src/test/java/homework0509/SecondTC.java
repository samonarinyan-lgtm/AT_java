package homework0509;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class SecondTC {

    WebDriver driver;


    @BeforeMethod
    public void create() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testSearchField() {
        driver.get("https://staff.am/en/jobs");

        WebElement input = driver.findElement(By.name("JobsFilter[key_word]"));
        input.sendKeys("qa");
        WebElement btn = driver.findElement(By.className("search-btn"));
        btn.click();

        WebElement clearBtn = driver.findElement(By.xpath("//a[contains(@class, 'clear')]"));
        Assert.assertTrue(clearBtn.isDisplayed());

        List<WebElement> jobsList = driver.findElements(By.xpath("//a[contains(@href, '/jobs/view/')]"));
        Assert.assertTrue(jobsList.size() > 0);

        WebElement input2 = driver.findElement(By.name("JobsFilter[key_word]"));
        input2.clear();
        input2.sendKeys("developer");
        input2.sendKeys(Keys.ENTER);

        WebElement clearBtn2 = driver.findElement(By.xpath("//a[contains(@class, 'clear')]"));
        Assert.assertTrue(clearBtn2.isDisplayed());

        WebElement input3 = driver.findElement(By.name("JobsFilter[key_word]"));
        input3.clear();
        input3.sendKeys("barev");
        WebElement btn2 = driver.findElement(By.className("search-btn"));
        btn2.click();

        WebElement emptyMsg = driver.findElement(By.className("empty"));
        Assert.assertTrue(emptyMsg.isDisplayed());

        WebElement input4 = driver.findElement(By.name("JobsFilter[key_word]"));
        input4.clear();
        input4.sendKeys(Keys.ENTER);

        List<WebElement> clearBtnList = driver.findElements(By.xpath("//a[contains(@class, 'clear')]"));
        Assert.assertEquals(clearBtnList.size(), 0);
        List<WebElement> restJobs = driver.findElements(By.xpath("//a[contains(@href, '/jobs/view/')]"));
        Assert.assertTrue(restJobs.size() > 0);
    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}