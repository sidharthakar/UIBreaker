package BitGo;

import DataReader.ConfigReader;
import Enums.TestCaseID;
import Enums.TestCaseType;
import Enums.TestType;
import UtilityManager.AssertUtility;
import UtilityManager.BrowserFactory;
import UtilityManager.DriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

public class BitcoinExplorer {
BitcoinExplorerPage bitcoinExplorerPage;
    @BeforeTest
    public void openBrowser() throws Exception {
        BrowserFactory.launchApplication("AssesmentURL");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), ConfigReader.ReadConfig.getconfigvalue("AssesmentURL"),"URL Validation");
        bitcoinExplorerPage= new BitcoinExplorerPage(DriverManager.getDriver());
    }
    @Test(priority = 1)
    @Description("Validation Of Header")
    @TestCaseID(TestCase_ID = "1235")
    @TestType(Type=TestCaseType.REGRESSION)
    public void heading_Validate(){
        if(bitcoinExplorerPage.findheader()){
            org.testng.Assert.assertEquals(bitcoinExplorerPage.HeaderValue.getText(),"25 of 2875 Transactions","Header Validation");
        }
    }

    @Test(priority = 2)
    @Description("Validation Of Input and OutPut Value")
    @TestCaseID(TestCase_ID = "1235")
    @TestType(Type=TestCaseType.REGRESSION)
    public void input_output_Value_validation(){
        bitcoinExplorerPage.ValidatedInputandOutPut();
    }


    @AfterSuite
    public void closeBrowser() throws Exception {
        BrowserFactory.tearDownBrowser();
    }
}
