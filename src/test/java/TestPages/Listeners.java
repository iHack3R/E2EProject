package TestPages;

import Resources.BaseFramework;
import Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseFramework implements ITestListener {

    ExtentTest test;
    //Running the Extent report method created
    ExtentReports extent = ExtentReporterNG.getReportObject();
    //Making the Extent report thread safe
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        //Creating the name of report by grabbing the name of failure method
        test = extent.createTest(result.getMethod().getMethodName());
        //Setting the ExtentTest variable test to ThreadLocal extentTest variable
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test is passed");
    }

    public void onTestFailure(ITestResult result) {

        //Error log is retrieved and sent to fail method
        extentTest.get().fail(result.getThrowable());

        WebDriver screenshotDriver;

        //Getting the name of method where error occurred
        String methodName = result.getMethod().getMethodName();
        try {
            //Getting the driver of the class where failure happened.
            screenshotDriver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try {
            //Setting screenshot to Extent report on failure
            extentTest.get().addScreenCaptureFromPath(getScreenShot(methodName,driver),result.getMethod().getMethodName());
            //Running the screenshot method created to take screenshots
            //getScreenShot(methodName,driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        //Flushing the extent report object
        extent.flush();
    }
}