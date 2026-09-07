package homework0109;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StaffAmTest extends BaseTest {

    @Test
    public void testStaffAmSearchAndClearFilters() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSwitchToStandardSearch();
        mainPage.selectRandomCategory();
        mainPage.clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        Assert.assertTrue(searchResultsPage.isClearFiltersButtonDisplayed(), "Clear filters button does not exist!");

        searchResultsPage.clickClearFilters();

        Assert.assertTrue(searchResultsPage.isClearFiltersButtonDisappeared(), "Clear filters button did not disappear!");
    }
}