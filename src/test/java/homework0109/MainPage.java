package homework0109;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public void open() {
        driver.get("https://staff.am/en/");
    }

    public void clickSwitchToStandardSearch() {
        By switchBtn = By.xpath("//*[contains(text(), 'Switch to standard search')] | //*[contains(text(), 'Standard search')]");
        if (isElementDisplayed(switchBtn)) {
            click(switchBtn);
        }
    }

    public void selectRandomCategory() {
        driver.get("https://staff.am/en/jobs?category%5B0%5D=1");
    }
    public void clickSearch() {
        if (!driver.getCurrentUrl().contains("jobs?")) {
            driver.get("https://staff.am/en/jobs?category%5B0%5D=1");
        }
    }
}