package projects.crm.com.web.crm.testcases;

import projects.crm.com.common.BaseSetup;
import projects.crm.com.driver.DriverManager;
import projects.crm.com.helpers.CaptureHelpers;
import projects.crm.com.helpers.PropertiesHelper;
import projects.crm.com.web.crm.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CaptureScreenTest {
    private WebDriver driver;
    private SignInPage signInPage;

    @BeforeClass
    public void createDriver() throws Exception {
        // Gọi hàm để khởi tạo file properties
        PropertiesHelper.loadAllFiles();

        // Đọc data từ file properties với key là "browser"
       // driver = new BaseSetup().setupDriver(PropertiesHelper.getValue("browser"));
        CaptureHelpers.startRecord("Test01");
    }

    @Test
    public void signinCRM() throws IOException {
        signInPage = new SignInPage();
        driver.get("https://app.hrsale.com/erp/login");
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "HRSALE | Log n");
        // Đọc data từ file properties
        signInPage.logIn(PropertiesHelper.getValue("email"),PropertiesHelper.getValue("password"));
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        Thread.sleep(1000);
        //Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Test Case
        //Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
        //passed = SUCCESS và failed = FAILURE
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                CaptureHelpers.takeScreenshot(result);
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void closeDriver() throws Exception {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
        CaptureHelpers.stopRecord();
    }
}


