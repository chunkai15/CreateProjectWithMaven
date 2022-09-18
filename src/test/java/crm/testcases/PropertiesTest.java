package crm.testcases;

import crm.base.BaseSetup;
import crm.common.utilities.PropertiesHelper;
import crm.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public void signinCRM(){
        signInPage = new SignInPage(driver);
        driver.get("https://hrm.anhtester.com/");

        // Đọc data từ file properties
        signInPage.login(PropertiesHelper.getValue("email"),PropertiesHelper.getValue("password"));

    }

    @AfterClass
    public void closeDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
