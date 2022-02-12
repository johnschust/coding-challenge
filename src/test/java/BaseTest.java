import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.ReportUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest extends ReportUtils {

    private final Logger logger = Logger.getLogger(BaseTest.class.getSimpleName());

    protected WebDriver webDriver;

    @BeforeClass
    protected void beforeClass() {
        String chromeDriverPath = Objects.requireNonNull(this.getClass().getResource("/chromedriver.exe")).getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().setPosition(new Point(0, 0));
        Dimension screenDimensions = new Dimension(1280, 768);
        webDriver.manage().window().setSize(screenDimensions);
        logInfo(String.format("Setting the screen resolution to width:  %s | height:  %s",
                screenDimensions.getWidth(), screenDimensions.getHeight()));
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        String testStartMessage = "\n---------------------------------------------------------\n\n" + "Starting: " +
                method.getName() + "\n\n---------------------------------------------------------";
        logInfo(testStartMessage);
        webDriver.get("http://www.amazon.com");
    }

    @AfterMethod
    protected void afterMethod(Method method) {
        String testCompleteMessage = String.format("Complete: %s", method.getName());
        logInfo(testCompleteMessage);
        webDriver.manage().deleteAllCookies();
    }

    @AfterClass
    protected void afterClass() {
//        webDriver.close();
    }

    private void logInfo(String message) {
        logger.log(Level.INFO, message);
    }
}
