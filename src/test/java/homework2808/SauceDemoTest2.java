package homework2808;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceDemoTest2 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @DataProvider(name = "userTypes")
    public Object[][] getUserTypes() {
        return new Object[][]{
                {"standard_user"},
                {"problem_user"},
                {"performance_glitch_user"},
                {"error_user"},
                {"visual_user"}
        };
    }

    @Test(dataProvider = "userTypes")
    public void testLoginWithAllUsers(String username) {
        driver.findElement(By.xpath("//input[@xpath1='true' or @id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        WebElement title = driver.findElement(By.xpath("//span[@class='title']"));
        Assert.assertTrue(title.isDisplayed());
        Assert.assertEquals(title.getText(), "Products");
    }

    @Test
    public void testPurchaseFlow() {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        WebElement tshirtAddToCartBtn = driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/ancestor::div[@class='inventory_item']//button"));
        tshirtAddToCartBtn.click();

        WebElement cartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        Assert.assertEquals(cartBadge.getText(), "1");

        cartBadge.click();

        WebElement cartItemName = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        Assert.assertEquals(cartItemName.getText(), "Sauce Labs Bolt T-Shirt");

        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Doe");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("0010");
        driver.findElement(By.xpath("//input[@id='continue']")).click();

        WebElement itemPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
        String itemPriceText = itemPrice.getText().replace("$", "");
        double price = Double.parseDouble(itemPriceText);

        WebElement tax = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        String taxText = tax.getText().replace("Tax: $", "");
        double taxAmount = Double.parseDouble(taxText);

        WebElement total = driver.findElement(By.xpath("//div[contains(@class, 'summary_total_label')]"));
        String totalText = total.getText().replace("Total: $", "");
        double totalAmount = Double.parseDouble(totalText);

        Assert.assertEquals(price + taxAmount, totalAmount);

        driver.findElement(By.xpath("//button[@id='finish']")).click();

        WebElement completeHeader = driver.findElement(By.xpath("//h2[@class='complete-header']"));
        Assert.assertEquals(completeHeader.getText(), "Thank you for your order!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}