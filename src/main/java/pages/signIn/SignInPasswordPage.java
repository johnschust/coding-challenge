package pages.signIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BaseFragment;

public class SignInPasswordPage extends SignInBasePage {

    private final By passwordBy = By.id("ap_password");
    protected final By signInBy = By.id("signInSubmit");

    public SignInPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Set text in the password input field.
     */
    public void setPassword(String password) {
        setText("Password", passwordBy, password);
    }

    /**
     * Clicks the sign-in button and returns a new instance of the current page.  This should only be used if a password
     * error is expected.
     * @return new instance of the sign in password page
     */
    public SignInPasswordPage clickSignInWithErrors() {
        click("Sign-In", signInBy);
        return new SignInPasswordPage(webDriver);
    }

    /**
     * Validates that the sign-in password page is displayed.
     */
    public boolean isDisplayedPage() {
        return isPresentAndDisplayed(passwordBy);
    }
}
