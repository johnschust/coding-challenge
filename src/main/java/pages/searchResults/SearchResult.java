package pages.searchResults;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BaseFragment;
import pages.ProductDetailsPage;

public class SearchResult extends BaseFragment {

    private final By productTitleBy;

    SearchResult(WebDriver webDriver, String containerSelector) {
        super(webDriver);
        productTitleBy = By.cssSelector(String.format("%s h2 a", containerSelector));
    }

    /**
     * Gets the product title for the search result.
     */
    public String getProductTitle() {
        return getText(productTitleBy);
    }

    /**
     * Clicks on the product title link.
     * @return the resulting product details page
     */
    public ProductDetailsPage clickProductTitle() {
        click("Product title", productTitleBy);
        return new ProductDetailsPage(webDriver);
    }
}
