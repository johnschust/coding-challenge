package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends AmazonBasePage {

    private final By cartPageBy = By.id("sc-active-cart");
    private final By productTitleBy = By.cssSelector(".sc-product-title .a-truncate-cut");
    private final By priceBy = By.className("sc-product-price");

    CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Get the name of the first product in the cart.
     */
    public String getProductTitle() {
        return getText(productTitleBy);
    }

    /**
     * Get the price of the first product in the cart.
     */
    public String getPrice() {
        return getText(priceBy);
    }

    /**
     * Validates that the cart page is displayed.
     */
    public boolean isDisplayedPage() {
        return isPresentAndDisplayed(cartPageBy);
    }
}
