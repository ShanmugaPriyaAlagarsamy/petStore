package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtility implements ITestListener {

    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String repName;

    public static void createTest(String testName) {
        if (extent != null) {
            test = extent.createTest(testName);
        }
    }

    public static void logInfo(String message) {
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    public static void logFailure(String message) {
        if (test != null) {
            test.log(Status.FAIL, message);
        }
    }

    @Override
    public void onStart(ITestContext testContext) {
        // Initialize the report with a timestamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Petstore-API-Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Configure the reporter
        sparkReporter.config().setDocumentTitle("Petstore API Test Report");
        sparkReporter.config().setReportName("Petstore API Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information
        extent.setSystemInfo("Application", "Petstore API");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", System.getProperty("user.name"));

       
        }
    

		/*
		 * @Override public void onTestSuccess(ITestResult result) {
		 * createTest(result.getMethod().getMethodName());
		 * logInfo(result.getMethod().getMethodName() + " passed."); }
		 */

    @Override
    public void onTestFailure(ITestResult result) {
        createTest(result.getMethod().getMethodName());
        logFailure(result.getMethod().getMethodName() + " failed.");
        logInfo(result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        createTest(result.getMethod().getMethodName());
        logInfo(result.getMethod().getMethodName() + " skipped.");
        logInfo(result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();

        // Open the report in the default browser
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
