package pages.signIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BaseFragment;

public abstract class SignInBasePage extends BaseFragment {

    private final By errorMessageBoxBy = By.id("auth-error-message-box");
    private final By errorMessageContentBy = By.cssSelector("#auth-error-message-box .a-alert-content");

    SignInBasePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Validates that the error message box is displayed.
     */
    public boolean isDisplayedErrorMessage() {
        return isPresentAndDisplayed(errorMessageBoxBy);
    }

    /**
     * Get the message contained within the error message box.
     */
    public String getErrorMessageContent() {
        return getText(errorMessageContentBy);
    }
}
