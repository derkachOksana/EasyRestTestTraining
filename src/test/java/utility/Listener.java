package utility;

import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;


public class Listener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.logger.pass(result.getName() + " passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.logger.fail(result.getName() + " failed.");
    }
}
