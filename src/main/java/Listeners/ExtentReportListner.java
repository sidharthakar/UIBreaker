package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

public class ExtentReportListner implements ITestListener, ISuiteListener {

        private static ExtentReports extent;
        private static ExtentTest test;

        // Configure the Extent Spark Reporter
        public static ExtentReports configureReport() {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/ExtentSparkReport.html");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Results");
            sparkReporter.config().setTheme(Theme.DARK); // You can choose Theme.STANDARD or Theme.DARK

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Host Name", "Your Machine");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User Name", "Your Name");

            return extent;
        }
        @Override
        public void onStart(ITestContext context) {
            extent = configureReport();
        }

        @Override
        public void onTestStart(ITestResult result) {
            test = extent.createTest(result.getMethod().getMethodName());
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            test.log(Status.PASS, "Test Case PASSED: " + result.getMethod().getMethodName());
        }

        @Override
        public void onTestFailure(ITestResult result) {
            test.log(Status.FAIL, "Test Case FAILED: " + result.getMethod().getMethodName());
            test.log(Status.FAIL, result.getThrowable());
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            test.log(Status.SKIP, "Test Case SKIPPED: " + result.getMethod().getMethodName());
        }

        @Override
        public void onFinish(ITestContext context) {
            extent.flush(); // Write the report to the file
        }
    }

