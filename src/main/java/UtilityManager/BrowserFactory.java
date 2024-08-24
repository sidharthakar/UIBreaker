package UtilityManager;

import DataReader.ConfigReader;
import Enums.Browsers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrowserFactory {
    public static String browser = System.getProperty("browser");

        static{
            if(browser == null){
               browser="chrome";
            }
        }

    public static void setBrowser() {
            if(Browsers.isValidBrowser(browser)){
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
    }
    public static void launchApplication(String UrlKey) throws Exception {
        try {
            if(Objects.isNull(DriverManager.getDriver())){
                setBrowser();
                Logger.info("Browser Launch");
                DriverManager.getDriver().navigate().to(ConfigReader.ReadConfig.getconfigvalue(UrlKey));
                Logger.info("URL USED FOR LAUNCH " +ConfigReader.ReadConfig.getconfigvalue(UrlKey) );
                DriverManager.getDriver().manage().deleteAllCookies();
                DriverManager.getDriver().manage().window().maximize();
            }
        } catch (Exception e) {
            Logger.error("Unable to Open the Application");
            throw new Exception(e.getMessage()+"Unable to Open the Application");
        }
    }
    public static void tearDownBrowser() throws Exception {
        try {
            DriverManager.getDriver().quit();
            DriverManager.unloadDriver();
            Logger.info("Browser Closed");
        } catch (Exception e) {
            Logger.error("Not Able To Close the Browser");
            throw new Exception(e.getMessage()+"Not Able To Close the Browser :- " + browser);
        }
    }

}
