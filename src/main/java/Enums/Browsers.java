package Enums;

public enum Browsers {

    CHROME,
    FIREFOX,
    EDGE,
    SAFARI,
    HEADLESS;

    public static boolean isValidBrowser(String browser) {
        try {
            Browsers.valueOf(browser.toUpperCase());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Not a valid browser:--- " + browser +" - " +e.getMessage() );
        }
    }

}
