package crm.base;

import crm.common.helpers.CaptureHelpers;
import crm.common.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    WebDriver driver;
    @Override
    public void onFinish(ITestContext result) {

    }

    @Override
    public void onStart(ITestContext result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Tên của testcase failed nhưng có phần trăm passed là:" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Đây là test case bị fail: " + result.getName());

        try {
            CaptureHelpers.captureScreenshot(BaseSetup.getDriver(), result.getName());
        } catch (Exception e) {
            Log.info("Exception while taking screenshot " + e.getMessage());
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("Đây là test case bị bỏ qua: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Đây là test case chạy thành công: " + result.getName());

    }
}
