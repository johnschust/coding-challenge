package pages.signIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInEmailMobilePage extends SignInBasePage {

    private final By emailOrMobileBy = By.id("ap_email");
    private final By continueBy = By.id("continue");

    public SignInEmailMobilePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Sets text in the email or mobile phone number input field.
     */
    public void setEmailOrMobile(String value) {
        setText("Email or mobile phone number", emailOrMobileBy, value);
    }

    /**
     * Clicks the continue button to proceed to the password entry screen.
     * @return the resulting password entry page
     */
    public SignInPasswordPage clickContinue() {
        click("Continue", continueBy);
        return new SignInPasswordPage(webDriver);
    }

    /**
     * Validates that the email/mobile sign in page is displayed.
     */
    public boolean isDisplayedPage() {
        return isPresentAndDisplayed(emailOrMobileBy);
    }
}
