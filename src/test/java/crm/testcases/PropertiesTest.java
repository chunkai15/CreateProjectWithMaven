package crm.testcases;

import crm.base.BaseSetup;
import crm.base.TestListener;
import crm.common.utilities.Log;
import crm.common.utilities.PropertiesHelper;
import crm.pages.SignInPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

@Listeners(TestListener.class)
public class PropertiesTest {

    private WebDriver driver;
    private SignInPage signInPage;

    @BeforeClass
    public void createDriver() {
        // Gọi hàm để khởi tạo file properties
        PropertiesHelper.loadAllFiles();

        // Đọc data từ file properties với key là "browser"
        driver = new BaseSetup().setupDriver(PropertiesHelper.getValue("browser"));
    }

    @Test
    public void signinCRM() throws IOException {
        Log.info("Đây là TC signinCRM");
        signInPage = new SignInPage(driver);
        driver.get("https://app.hrsale.com/erp/login");

        // Đọc data từ file properties
        signInPage.login(PropertiesHelper.getValue("email"),PropertiesHelper.getValue("password"));
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        Thread.sleep(1000);
        //Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Test Case
        //Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
        //passed = SUCCESS và failed = FAILURE
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // Tạo tham chiếu của TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Gọi hàm capture screenshot - getScreenshotAs
                File source = ts.getScreenshotAs(OutputType.FILE);
                //Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
                File theDir = new File("./Screenshots/");
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                // result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
                FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Đã chụp màn hình: " + result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void closeDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
