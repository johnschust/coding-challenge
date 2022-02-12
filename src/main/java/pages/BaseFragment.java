package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BaseFragment {

    protected WebDriver webDriver;

    public BaseFragment(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Sets the text value for a web element.
     * @param fieldName name or label of the input field used for logging
     * @param by locator for the web element
     * @param text value that will be set in the text field
     */
    protected void setText(String fieldName, By by, String text) {
        System.out.printf("Set text for field '%s' to '%s'", fieldName, text);
        findElement(by).sendKeys(text);
    }

    /**
     * Clicks on a single web element
     * @param fieldName name of the element being clicked used for logging
     * @param by locator for the web element that will be clicked
     */
    protected void click(String fieldName, By by) {
        System.out.printf("Click: %s\n", fieldName);
        findElement(by).click();
    }

    /**
     * Select an option from a select web element using the option display text
     * @param fieldName name or label of the field used for logging
     * @param by locator for the select element
     * @param option display text for the option that will be selected
     */
    protected void selectOption(String fieldName, By by, String option) {
        System.out.printf("Select option '%s' from '%s'", fieldName, option);
        Select select = new Select(findElement(by));
        select.selectByVisibleText(option);
    }

    /**
     * Gets the displayed text for a web element that can be found with the given locator.
     * @param by locator that points to a single web element
     * @return text displayed in the web elemen
     */
    protected String getText(By by) {
        return findElement(by).getText();
    }

    /**
     * Gets an attribute value for a web element
     */
    protected String getAttribute(WebElement webElement, String attribute) {
        return webElement.getAttribute(attribute);
    }

    /**
     * Finds a single web element on the current page.
     * @param by locator for an element on the currently displayed page
     * @return web element
     */
    private WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    /**
     * Finds all web elements on the current page that match the given locator.
     * @param by locator for one or more elements on the curretly displayed page
     * @return list of web elements
     */
    protected List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    /**
     * Used to validate that a web element is present in the DOM and displayed to the users.
     * @param by locator for the web element
     * @return true is the element is present and displayed; false if the element is not present or displayed
     */
    protected boolean isPresentAndDisplayed(By by) {
        try {
            WebElement webElement = findElement(by);
            return webElement.isDisplayed();
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Waits up to 5 seconds for an element to be displayed.  Useful when selenium tries to continue before the page is
     * ready.
     * @param by locator for the web element we are waiting for
     */
    protected void waitForElementDisplayed(By by) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
