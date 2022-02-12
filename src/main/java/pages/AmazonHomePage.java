package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage extends AmazonBasePage {

    private final By imageCarouselBy = By.id("desktop-banner"); // Unique to amazon home/starting page

    public AmazonHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Used to validate that the user is on the home/start page.
     * @return true if an element unique to the page is found; false if it is not
     */
    public boolean isDisplayedPage() {
        return isPresentAndDisplayed(imageCarouselBy);
    }
}
