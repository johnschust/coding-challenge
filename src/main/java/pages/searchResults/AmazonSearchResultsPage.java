package pages.searchResults;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchResultsPage extends BaseFragment {

    private final By searchResultsBy = By.cssSelector("[cel_widget_id*='MAIN-SEARCH_RESULTS-']");

    public AmazonSearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Returns a single search result from the page that has a name that matches an expected product name.  This should
     * be used instead of grabbing the first search result since sponsored products will randomly appear at the top of
     * the search results.
     * @param productName exact product name to find in the search results
     * @return single search result with a matching product name
     */
    public SearchResult getSearchResultByProductName(String productName) {
        for(WebElement webElement : findElements(searchResultsBy)) {
            SearchResult searchResult = getSearchResult(webElement);
            if(searchResult.getProductTitle().equals(productName)) {
                return searchResult;
            }
        }
        return null;
    }

    /**
     * Get all the search results displayed on the page.
     * @return list of search results
     */
    public List<SearchResult> getSearchResults() {
        List<SearchResult> searchResultList = new ArrayList<>();
        for(WebElement webElement : findElements(searchResultsBy)) {
            searchResultList.add(getSearchResult(webElement));
        }
        return searchResultList;
    }

    /**
     * Validates that the page is displayed.
     */
    public boolean isDisplayedPage() {
        return isPresentAndDisplayed(searchResultsBy);
    }

    private SearchResult getSearchResult(WebElement webElement) {
        String selector = String.format("[cel_widget_id='%s']", getAttribute(webElement, "cel_widget_id"));
        return new SearchResult(webDriver, selector);
    }
}
