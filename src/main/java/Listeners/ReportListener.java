package Listeners;

import Enums.TestCaseType;
import Enums.TestType;
import UtilityManager.DriverManager;
import UtilityManager.Logger;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import static DataReader.ConfigReader.ReadConfig.env;
import static UtilityManager.BrowserFactory.browser;

public class ReportListener implements ISuiteListener, ITestListener {

    public static void setAllureReportName(String reportName) {
        Properties props = new Properties();
        props.setProperty("allure.report.name", reportName);

        FileOutputStream fos = null;
        try {
            File allurePropsFile = new File("target/allure-results/allure.properties");
            fos = new FileOutputStream(allurePropsFile);
            props.store(fos, "Allure Report Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void onStart(ISuite suite) {
            setAllureReportName("UIBreaker Report"+ LocalDateTime.now());
            Logger.info("UIBreaker Test Started" + suite.getXmlSuite().getName() + " " + suite.getName()
                                                 +" " + "Browser Used " + browser+" " + "Env Used " + env);
    }

    public void onFinish(ISuite suite) {
        DriverManager.unloadDriver();


    }

    @Override
    public void onTestStart(ITestResult result) {
        Logger.info("Test Started " + result.getMethod().getMethodName());
        TestType testTypeAnnotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestType.class);
        if (testTypeAnnotation != null) {
            TestCaseType testCaseType = testTypeAnnotation.Type();
            Allure.parameter("TestType", testCaseType.name());
        }


    }
    @Override
    public void onTestSuccess(ITestResult result) {
        Logger.info("Test Completed " + result.getMethod().getMethodName());
        byte[] screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Success Test "+result.getMethod().getMethodName(),new ByteArrayInputStream(screenshot));
    }
    @Override
    public void onTestFailure(ITestResult result) {
        Logger.info("Test Failed " + result.getMethod().getMethodName());
        byte[] screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Failed Test "+result.getMethod().getMethodName(),new ByteArrayInputStream(screenshot));
        Allure.addAttachment("Failed TestCaseType : -" , result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestType.class).Type().toString());
    }





}
