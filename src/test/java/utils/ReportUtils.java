package utils;

import org.testng.Assert;

public class ReportUtils {

    public static void logStep(String step) {
        System.out.printf("Step: %s\n", step);
    }

    public static void assertNotNull(String log, Object object) {
        System.out.printf("Validate: %s | %s\n", log, object);
        Assert.assertNotNull(object);
    }

    public static void assertEquals(String log, Object actual, Object expected) {
        System.out.printf("Validate: %s | Actual: %s | Expected %s\n", log, actual, expected);
        Assert.assertEquals(actual, expected);
    }

    public static void assertTrue(String log, boolean condition) {
        System.out.printf("Validate: %s | %s\n", log, condition);
        Assert.assertTrue(condition);
    }

    public static void assertFalse(String log, boolean condition) {
        System.out.printf("Validate: %s | %s\n", log, condition);
        Assert.assertFalse(condition);
    }
}
