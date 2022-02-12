package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends AmazonBasePage {

    private final By productPageBy = By.id("dp-container");
    private final By itemTitleBy = By.id("productTitle");
    private final By addToCartBy = By.id("add-to-cart-button");
    private final By priceSymbolBy = By.className("a-price-symbol");
    private final By priceWholeNumberBy = By.className("a-price-whole");
    private final By priceFractionBy = By.className("a-price-fraction");

    public ProductDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Get the name of the product.
     */
    public String getProductTitle() {
        return getText(itemTitleBy);
    }

    /**
     * Get the price of the product.
     */
    public String getPrice() {
        String priceSymbol = getText(priceSymbolBy);
        String priceWholeNumber = getText(priceWholeNumberBy);
        String priceFraction = getText(priceFractionBy);
        return String.format("%s%s.%s", priceSymbol, priceWholeNumber, priceFraction);
    }

    /**
     * Adds the current product to the cart.
     * @return the resulting Added to Cart slide out
     */
    public AddedToCartSlideOut clickAddToCart() {
        click("Add to Cart", addToCartBy);
        return new AddedToCartSlideOut(webDriver);
    }

    /**
     * Validates that the product details page is displayed.
     */
    public boolean isDisplayedPage() {
        return isPresentAndDisplayed(productPageBy);
    }
}
