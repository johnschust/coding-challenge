package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.searchResults.AmazonSearchResultsPage;
import pages.signIn.SignInEmailMobilePage;

public abstract class AmazonBasePage extends BaseFragment {

    private final By signInBy = By.id("nav-link-accountList-nav-line-1");
    private final By searchBarBy = By.id("twotabsearchtextbox");
    private final By searchDropdownBy = By.id("searchDropdownBox");
    private final By searchButtonBy = By.id("nav-search-submit-button");

    public AmazonBasePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Set the search bar text value.
     */
    public void setSearchText(String searchText) {
        setText("Search text", searchBarBy, searchText);
    }

    /**
     * Initiates a product search based on values set in the search dropdown and text field.
     * @return the resulting search results page
     */
    public AmazonSearchResultsPage clickSearch() {
        click("Search button", searchButtonBy);
        return new AmazonSearchResultsPage(webDriver);
    }

    /**
     * Clicks on the sign-in link at the top of the page.
     * @return the resulting email/mobile sign-in page.
     */
    public SignInEmailMobilePage clickSignIn() {
        click("Hello, Sign in", signInBy);
        return new SignInEmailMobilePage(webDriver);
    }

    /**
     * Selects a product filter for product search.
     */
    public void selectSearchDropdown(String option) {
        selectOption("Search filter dropdown", searchDropdownBy, option);
    }
}
