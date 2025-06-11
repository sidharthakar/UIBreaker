package UtilityManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptExecutorUtils {
    private JavaScriptExecutorUtils() {
        // Utility class
    }

    // Helper method to get JavascriptExecutor instance
    private static JavascriptExecutor getJsExecutor() {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call DriverManager.setDriver() first.");
        }
        if (!(driver instanceof JavascriptExecutor)) {
            throw new UnsupportedOperationException("WebDriver does not support JavascriptExecutor.");
        }
        return (JavascriptExecutor) driver;
    }

    /**
     * Executes a given JavaScript snippet.
     *
     * @param script The JavaScript code to execute.
     * @param args   Optional arguments to pass to the script.
     * @return The result of the JavaScript execution (can be null, boolean, Long, String, List, Map, or WebElement).
     */
    public static Object executeScript(String script, Object... args) {
        return getJsExecutor().executeScript(script, args);
    }

    /**
     * Executes a given asynchronous JavaScript snippet.
     *
     * @param script The JavaScript code to execute asynchronously.
     * @param args   Optional arguments to pass to the script.
     * @return The result of the JavaScript execution.
     */
    public static Object executeAsyncScript(String script, Object... args) {
        return getJsExecutor().executeAsyncScript(script, args);
    }

    // --- Element Interaction ---

    /**
     * Clicks on a web element using JavaScript. Useful for elements that are covered or not directly clickable by Selenium.
     *
     * @param element The WebElement to click.
     */
    public static void clickElement(WebElement element) {
        executeScript("arguments[0].click();", element);
    }

    /**
     * Sets the value of an input field using JavaScript. Can bypass some client-side validations.
     *
     * @param element The input WebElement.
     * @param value   The value to set.
     */
    public static void setInputValue(WebElement element, String value) {
        executeScript("arguments[0].value=arguments[1];", element, value);
    }

    /**
     * Gets the text content of an element using JavaScript.
     *
     * @param element The WebElement to get text from.
     * @return The text content as a String.
     */
    public static String getElementText(WebElement element) {
        return (String) executeScript("return arguments[0].textContent;", element);
    }

    /**
     * Gets the inner HTML of an element using JavaScript.
     *
     * @param element The WebElement to get inner HTML from.
     * @return The inner HTML as a String.
     */
    public static String getElementInnerHtml(WebElement element) {
        return (String) executeScript("return arguments[0].innerHTML;", element);
    }

    /**
     * Gets the value of a specified attribute of an element using JavaScript.
     *
     * @param element   The WebElement.
     * @param attribute The name of the attribute.
     * @return The attribute value as a String.
     */
    public static String getElementAttribute(WebElement element, String attribute) {
        return (String) executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }

    // --- Scrolling ---

    /**
     * Scrolls the window down by a specified number of pixels.
     *
     * @param pixels The number of pixels to scroll down.
     */
    public static void scrollWindowDown(int pixels) {
        executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    /**
     * Scrolls the window up by a specified number of pixels.
     *
     * @param pixels The number of pixels to scroll up.
     */
    public static void scrollWindowUp(int pixels) {
        executeScript("window.scrollBy(0, -arguments[0]);", pixels);
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public static void scrollPageToBottom() {
        executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls to the top of the page.
     */
    public static void scrollPageToTop() {
        executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls a specific element into view.
     *
     * @param element The WebElement to scroll to.
     */
    public static void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls a specific element into view at the top of the viewport.
     *
     * @param element The WebElement to scroll to.
     * @param alignToTop If true, the top of the element will be aligned to the top of the visible area of the scrollable ancestor.
     * If false, the bottom of the element will be aligned to the bottom of the visible area of the scrollable ancestor.
     */
    public static void scrollIntoView(WebElement element, boolean alignToTop) {
        executeScript("arguments[0].scrollIntoView(arguments[1]);", element, alignToTop);
    }

    // --- Browser Actions ---

    /**
     * Refreshes the current page using JavaScript.
     */
    public static void refreshPage() {
        executeScript("location.reload();");
    }

    /**
     * Navigates to a specific URL using JavaScript.
     *
     * @param url The URL to navigate to.
     */
    public static void navigateToUrl(String url) {
        executeScript("window.location.href = arguments[0];", url);
    }

    /**
     * Gets the current page title using JavaScript.
     *
     * @return The page title as a String.
     */
    public static String getPageTitle() {
        return (String) executeScript("return document.title;");
    }

    /**
     * Gets the current page URL using JavaScript.
     *
     * @return The page URL as a String.
     */
    public static String getPageUrl() {
        return (String) executeScript("return window.location.href;");
    }

    /**
     * Navigates back in browser history using JavaScript.
     */
    public static void goBack() {
        executeScript("history.back();");
    }

    /**
     * Navigates forward in browser history using JavaScript.
     */
    public static void goForward() {
        executeScript("history.forward();");
    }

    // --- Styling and Visibility ---

    /**
     * Hides a web element by setting its display style to 'none'.
     *
     * @param element The WebElement to hide.
     */
    public static void hideElement(WebElement element) {
        executeScript("arguments[0].style.display='none';", element);
    }

    /**
     * Shows a web element by removing its display style.
     *
     * @param element The WebElement to show.
     */
    public static void showElement(WebElement element) {
        executeScript("arguments[0].style.display='block';", element); // Or 'initial', 'unset', etc. depending on original display type
    }

    /**
     * Highlights a web element with a temporary border.
     * Useful for debugging or demonstrating actions.
     *
     * @param element The WebElement to highlight.
     */
    public static void highlightElement(WebElement element) {
        String originalStyle = (String) executeScript("return arguments[0].style.border", element);
        executeScript("arguments[0].style.border='3px solid red';", element);
        // You might add a Thread.sleep(500) here if you want to see the highlight
        // before reverting, but generally, Selenium doesn't use Thread.sleep.
        // For a more robust solution, you'd execute another script to revert after a delay.
        executeScript("arguments[0].style.border='" + originalStyle + "';", element);
    }

    /**
     * Adds a border to a web element.
     *
     * @param element The WebElement to add border to.
     * @param color   The color of the border (e.g., "red", "#FF0000").
     * @param width   The width of the border in pixels (e.g., "2px").
     * @param style   The style of the border (e.g., "solid", "dashed").
     */
    public static void addBorder(WebElement element, String color, String width, String style) {
        executeScript("arguments[0].style.border='" + width + " " + style + " " + color + "';", element);
    }

    // --- Checkbox/Radio Button ---

    /**
     * Checks a checkbox or selects a radio button using JavaScript.
     *
     * @param element The checkbox/radio button WebElement.
     */
    public static void checkElement(WebElement element) {
        executeScript("arguments[0].checked = true;", element);
    }

    /**
     * Unchecks a checkbox using JavaScript.
     *
     * @param element The checkbox WebElement.
     */
    public static void uncheckElement(WebElement element) {
        executeScript("arguments[0].checked = false;", element);
    }

    // --- Get Browser Information ---

    /**
     * Gets the user agent string of the browser.
     *
     * @return The user agent string.
     */
    public static String getUserAgent() {
        return (String) executeScript("return navigator.userAgent;");
    }

    /**
     * Gets the inner width of the browser window (viewport width).
     *
     * @return The inner width in pixels.
     */
    public static long getWindowInnerWidth() {
        return (Long) executeScript("return window.innerWidth;");
    }

    /**
     * Gets the inner height of the browser window (viewport height).
     *
     * @return The inner height in pixels.
     */
    public static long getWindowInnerHeight() {
        return (Long) executeScript("return window.innerHeight;");
    }

    // --- Miscellaneous ---

    /**
     * Removes an element from the DOM. Use with caution.
     *
     * @param element The WebElement to remove.
     */
    public static void removeElement(WebElement element) {
        executeScript("arguments[0].remove();", element);
    }

    /**
     * Simulates pressing the Enter key on an element (e.g., after typing in a text field).
     * Note: This is a common JavaScript event, but might not work for all elements or complex scenarios.
     * Selenium's `sendKeys(Keys.ENTER)` is usually preferred if the element is interactable.
     *
     * @param element The WebElement on which to simulate Enter.
     */
    public static void simulateEnterKey(WebElement element) {
        executeScript("var event = new KeyboardEvent('keydown', { 'key': 'Enter' }); arguments[0].dispatchEvent(event);", element);
    }

    /**
     * Gets the current document ready state.
     *
     * @return The document ready state (e.g., "loading", "interactive", "complete").
     */
    public static String getDocumentReadyState() {
        return (String) executeScript("return document.readyState;");
    }

    /**
     * Waits until the document ready state is 'complete'.
     *
     * @param driver The WebDriver instance.
     * @param timeoutInSeconds The maximum time to wait in seconds.
     */
    public static void waitUntilPageLoads(long timeoutInSeconds) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call DriverManager.setDriver() first.");
        }
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
