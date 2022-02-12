import org.testng.annotations.Test;
import pages.*;
import pages.searchResults.AmazonSearchResultsPage;
import pages.searchResults.SearchResult;
import pages.signIn.SignInEmailMobilePage;
import pages.signIn.SignInPasswordPage;
import utils.ReportUtils;

import java.util.List;
import java.util.Objects;

public class TestAmazonShopping extends AmazonBaseTest {

    @Documentation(
            coverage = "Validates that at least 10 products are displayed after doing a simple Amazon search. " +
                    "Validates that the top matched products all contain at least one term used in the search.",
            createdDate = "2/12/2022"
    )
    @Test
    public void testProductSimpleSearch() {
        String simpleSearchText = "Robin Hood";
        String[] searchTerms = simpleSearchText.split(" ");

        logStep("Perform a simple product search.");
        AmazonHomePage homePage = new AmazonHomePage(webDriver);
        ReportUtils.assertTrue("Home page is displayed.", homePage.isDisplayedPage());
        homePage.setSearchText(simpleSearchText);
        AmazonSearchResultsPage searchResultsPage = homePage.clickSearch();
        assertTrue("Search results page is displayed.", searchResultsPage.isDisplayedPage());

        List<SearchResult> searchResultList = searchResultsPage.getSearchResults();
        // At least one search result must be displayed for the test to be valid.
        assertTrue("At least ten search result are displayed.", searchResultList.size() >= 10);
        for(int i = 0; i < 10; i++) {
            SearchResult searchResult = searchResultList.get(i);
            String message = String.format("Product name '%s' contains at least one term in '%s'", searchResult.getProductTitle(), simpleSearchText);
            assertTrue(message, nameContainsTermFromArray(searchResult.getProductTitle(), searchTerms));
        }
    }

    private boolean nameContainsTermFromArray(String name, String[] terms) {
        for(String term : terms) {
            if(name.contains(term)) {
                return true;
            }
        }
        return false;
    }

    @Documentation(
            coverage = "Validates that users can search for a specific product on Amazon.com.  Validates that the " +
                    "product can be added to a cart.  Validates that the correct product appears and price appears " +
                    "in the cart.",
            createdDate = "2/12/2022"
    )
    @Test
    public void testAddProductToCart() {
        String expectedProductName = "Burpee Self-Watering Seed Starter Tray, 72 Cells";

        ReportUtils.logStep("Search for a specific product.");
        AmazonHomePage homePage = new AmazonHomePage(webDriver);
        ReportUtils.assertTrue("Home page is displayed.", homePage.isDisplayedPage());
        homePage.selectSearchDropdown("Garden & Outdoor");
        homePage.setSearchText(expectedProductName);
        AmazonSearchResultsPage searchResultsPage = homePage.clickSearch();

        ReportUtils.assertTrue("Search results page is displayed.", searchResultsPage.isDisplayedPage());
        SearchResult searchResult = searchResultsPage.getSearchResultByProductName(expectedProductName);
        ReportUtils.assertNotNull(String.format("Search result appears for '%s'", expectedProductName), searchResult);

        ReportUtils.logStep("View the product details page.");
        ProductDetailsPage productDetailsPage = Objects.requireNonNull(searchResult).clickProductTitle();
        ReportUtils.assertTrue("Product details page is displayed.", productDetailsPage.isDisplayedPage());
        ReportUtils.assertEquals("Product name", expectedProductName, productDetailsPage.getProductTitle());
        String productPrice = productDetailsPage.getPrice();

        ReportUtils.logStep("Add the product to your cart.");
        AddedToCartSlideOut addedToCartSlideOut = productDetailsPage.clickAddToCart();
        ReportUtils.assertTrue("Slide out is displayed.", addedToCartSlideOut.isDisplayedSlideOut());

        ReportUtils.logStep("View your cart.");
        CartPage cartPage = addedToCartSlideOut.clickCart();
        ReportUtils.assertTrue("Cart page is displayed.", cartPage.isDisplayedPage());
        ReportUtils.assertEquals("Product name", expectedProductName, cartPage.getProductTitle());
        ReportUtils.assertEquals("Product Price", productPrice, cartPage.getPrice());
    }

    @Documentation(
            coverage = "Validates that users cannot login by entering invalid credentials.  Validates that the user " +
                    "is informed that the credentials are incorrect.",
            createdDate = "2/12/2022"
    )
    @Test
    public void testLoginSecurity() {
        logStep("Navigate to the login page from the Amazon home/start page.");
        AmazonHomePage amazonHomePage = new AmazonHomePage(webDriver);
        SignInEmailMobilePage signInEmailMobilePage = amazonHomePage.clickSignIn();
        assertTrue("Email/mobile sign-in page is displayed.", signInEmailMobilePage.isDisplayedPage());

        logStep("Continue the sign-in process using a valid email.");
        signInEmailMobilePage.setEmailOrMobile("johnschust@gmail.com");
        SignInPasswordPage signInPasswordPage = signInEmailMobilePage.clickContinue();
        assertTrue("Sign-in password entry page is displayed.", signInPasswordPage.isDisplayedPage());
        assertFalse("Error message is not displayed.", signInPasswordPage.isDisplayedErrorMessage());

        logStep("Attempt to complete the sign-in process with an invalid password.");
        signInPasswordPage.setPassword("INVALID_PASSWORD");
        signInPasswordPage = signInPasswordPage.clickSignInWithErrors();
        assertTrue("Sign-in password entry page is displayed.", signInPasswordPage.isDisplayedPage());
        assertTrue("Error message is displayed.", signInPasswordPage.isDisplayedErrorMessage());
        assertEquals("Error message contents", signInPasswordPage.getErrorMessageContent(), "Your password is incorrect");
    }
}
