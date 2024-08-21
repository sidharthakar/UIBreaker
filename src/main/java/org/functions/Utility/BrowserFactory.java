package org.functions.Utility;

import groovy.util.logging.Slf4j;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrowserFactory {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(BrowserFactory.class);
    public static String browser = System.getProperty("browser");

        static{
            if(browser == null){
               browser="chrome";
            }
        }

    public static void setBrowser() {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
                options.addArguments("ignore-certificate-errors");
                DriverManager.setDriver(new ChromeDriver(options));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("ignore-certificate-errors");
                DriverManager.setDriver(new FirefoxDriver(firefoxOptions));
                break;
            case "ie":
                DriverManager.setDriver(new InternetExplorerDriver());
                break;
            default:
                throw new UnsupportedOperationException("Unsupported browser: " + BrowserFactory.browser);
        }
    }
    public static void launchApplication(String UrlKey) throws Exception {
        try {
            if(Objects.isNull(DriverManager.getDriver())){
                setBrowser();
                DriverManager.getDriver().navigate().to(ConfigReader.ReadConfig.getconfigvalue(UrlKey));
                DriverManager.getDriver().manage().deleteAllCookies();
                DriverManager.getDriver().manage().window().maximize();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage()+"Unable to Open the Application");
        }
    }
    public static void tearDownBrowser() throws Exception {
        try {
            DriverManager.getDriver().quit();
            DriverManager.unloadDriver();
        } catch (Exception e) {
            throw new Exception(e.getMessage()+"Not Able To Close the Browser :- " + browser);
        }
    }

}
