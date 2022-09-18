package crm.testcases;

import crm.base.BaseSetup;
import crm.common.helpers.ExcelHelpers;
import crm.pages.DashboardPage;
import crm.pages.LoginBT;
import crm.pages.ProjectPage;
import crm.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest {

    private WebDriver driver;
    private SignInPage signInPage;
    private LoginBT loginPage;
    private ExcelHelpers excel;


    @BeforeClass
    public void setUp() {
        //driver = getDriver();
        driver = new BaseSetup().setupDriver("chrome");
        signInPage = new SignInPage(driver);
        excel = new ExcelHelpers();
    }

    @Test(priority = 1)
    public void signIn() throws Exception {
        excel.setExcelFile("src/test/resources/Book1.xlsx", "Sheet1");
        driver.get("https://crm.anhtester.com/signin");
        signInPage.login(excel.getCellData("username", 1), excel.getCellData("password", 1) );

    }

    @Test(priority = 1)
    public void signInReadExcelDynamic() throws Exception {
        excel.setExcelFile("src/test/resources/Book1.xlsx", "Sheet1");
        driver.get("https://crm.anhtester.com/signin");

        for (int i=0; i<6; i++){
            signInPage.login(excel.getCellData("username", 1), excel.getCellData("password", 1) );
        }


    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
