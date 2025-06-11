import DataReader.ConfigReader;
import Enums.Browsers;
import Enums.TestCaseID;
import Enums.TestCaseType;
import Enums.TestType;
import UtilityManager.BrowserFactory;
import UtilityManager.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Driver;


public class Test {
    @org.testng.annotations.Test
    public void setUp() throws Exception {
        System.setProperty(BrowserFactory.browser,"Chrome");
        System.setProperty(ConfigReader.ReadConfig.env,"qa");
        BrowserFactory.launchApplication("URL");
    }
    @TestCaseID(TestCase_ID = "123456")
    @TestType(Type = TestCaseType.REGRESSION)
    @org.testng.annotations.Test
    public void test() throws Exception {
       System.out.println(DriverManager.getDriver().getCurrentUrl());
    }
    @AfterTest
    public void tearDown() throws Exception {
        BrowserFactory.tearDownBrowser();
    }

}
