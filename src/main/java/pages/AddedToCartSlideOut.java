package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddedToCartSlideOut extends BaseFragment {

    private final By slideOutBy = By.id("attach-accessory-pane");
    private final By cartBy = By.cssSelector("#attach-sidesheet-view-cart-button input");

    AddedToCartSlideOut(WebDriver webDriver) {
        super(webDriver);
        waitForElementDisplayed(slideOutBy);
    }

    /**
     * Clicks on the cart button.
     * @return the resulting active cart page.
     */
    public CartPage clickCart() {
        click("Cart", cartBy);
        return new CartPage(webDriver);
    }

    /**
     * Validates that the slide out is open and displayed.
     */
    public boolean isDisplayedSlideOut() {
        return isPresentAndDisplayed(slideOutBy);
    }
}
