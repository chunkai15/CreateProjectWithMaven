package projects.crm.com.listeners;

import com.aventstack.extentreports.Status;
import projects.crm.com.report.AllureManager;
import projects.crm.com.report.ExtentTestManager;
import projects.crm.com.utils.Log;
import projects.crm.com.helpers.CaptureHelpers;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import projects.crm.com.report.ExtentReportManager;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        System.out.println("Start Suite: " + result.getStartDate());
        CaptureHelpers.startRecord(result.getName());
    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Finish Suite: " + result.getEndDate());
        CaptureHelpers.stopRecord();
        ExtentReportManager.getExtentReports().flush(); //Kết thúc và thực thi xuất report ra file

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(result.getName() + " is pass.");
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //System.out.println(result.getName() + " is fail.");
        CaptureHelpers.takeScreenshot(result); //Chụp màn hình khi Fail
        Log.error(result.getName() + " is fail.");

        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is fail.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getName() + " is skip.");
        ExtentTestManager.logMessage(Status.SKIP, result.getName() + " is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

}