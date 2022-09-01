package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener {
    ExtentReports extent = ExtentReporter.setupReporter();
    ExtentTest test;
    private final static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass(result.getName() + " passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().info("Test failures because of: " + result.getThrowable());
        extentTest.get().fail(result.getName() + " failed.");
    }

    @Override
    public void onFinish(ITestContext context)    {
        extent.flush();
    }
}
