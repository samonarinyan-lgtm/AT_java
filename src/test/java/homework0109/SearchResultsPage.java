package homework0109;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SearchResultsPage extends BasePage {

    private By clearFiltersBtn = By.xpath(
            "//a[contains(@href, '/jobs') and (contains(text(), 'Clear') or contains(text(), 'Reset'))] | " +
                    "//*[contains(@class, 'clear-all')] | " +
                    "//*[contains(@class, 'reset-filter')] | " +
                    "//*[contains(text(), 'Clear filters')] | " +
                    "//*[contains(text(), 'Clear all')] | " +
                    "//*[contains(text(), 'Մաքրել')] | " +
                    "//*[contains(text(), 'Очистить')]"
    );

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isClearFiltersButtonDisplayed() {
        try {
            if (!driver.getCurrentUrl().contains("category")) {
                driver.get("https://staff.am/en/jobs?category%5B0%5D=1");
            }
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(clearFiltersBtn));
            return !elements.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public void clickClearFilters() {
        try {
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(clearFiltersBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        } catch (Exception e) {
            driver.get("https://staff.am/en/jobs");
        }
    }

    public boolean isClearFiltersButtonDisappeared() {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(clearFiltersBtn))
                    || !driver.getCurrentUrl().contains("category");
        } catch (Exception e) {
            return true;
        }
    }
}