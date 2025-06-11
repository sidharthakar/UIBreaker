package UtilityManager; // Corrected package name as provided

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;


public class WaitUtils { // Changed class name to proper PascalCase (WaitUtils) for convention


    private static WebDriverWait wait;
    private static final long DEFAULT_TIMEOUT_SECONDS = 15;

    static {

        WebDriver driver = DriverManager.getDriver(); // Get driver from DriverManager
        if (driver == null) {
            System.err.println("Warning: WebDriver is not initialized when WaitUtils is loaded. " +
                    "Ensure DriverManager.setDriver() is called before using WaitUtils.");

        }

        if (driver != null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        }
    }

    public static void initialize(long timeoutInSeconds) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call DriverManager.setDriver() first.");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public static void initialize() {
        initialize(DEFAULT_TIMEOUT_SECONDS);
    }


    public static WebElement waitForElementToBeVisible(By locator) {
        if (wait == null) {
            initialize();
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForTextToBePresentInElement(By locator, String text) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static boolean waitForElementToBeInvisible(By locator) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForAllElementsToBeVisible(By locator) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static WebElement waitForElementPresence(By locator) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // --- Wait Methods accepting WebElement ---

    public static WebElement waitForElementToBeVisible(WebElement element) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitForTextToBePresentInElement(WebElement element, String text) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static boolean waitForElementToBeInvisible(WebElement element) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // --- Other useful static wait methods ---

    public static boolean waitForUrlContains(String urlContains) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.urlContains(urlContains));
    }

    public static boolean waitForTitleContains(String titleContains) {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.titleContains(titleContains));
    }

    public static Alert waitForAlertPresent() {
        if (wait == null) initialize();
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // --- Static Fluent Wait ---

    public static <T> T fluentWait(Function<WebDriver, T> expectedCondition,
                                   long timeoutInSeconds,
                                   long pollingEveryMillis,
                                   Class<? extends Throwable>... ignoreExceptions) {
        // FluentWait needs a WebDriver instance directly
        FluentWait<WebDriver> fluentWait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingEveryMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        for (Class<? extends Throwable> exception : ignoreExceptions) {
            fluentWait.ignoring(exception);
        }

        return fluentWait.until(expectedCondition);
    }
}