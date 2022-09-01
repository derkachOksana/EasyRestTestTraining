package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
    static ExtentReports extent;

    public static ExtentReports setupReporter() {
        ExtentSparkReporter spark = new ExtentSparkReporter(ConfProperties.getProperty("reports.dir")
                + DateHandler.getCurrentDateTime() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}
