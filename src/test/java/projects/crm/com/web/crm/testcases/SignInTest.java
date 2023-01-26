package projects.crm.com.web.crm.testcases;

import org.testng.annotations.*;
import projects.crm.com.common.BaseSetup;
import projects.crm.com.helpers.ExcelHelpers;
import projects.crm.com.web.crm.pages.SignInPage;

public class SignInTest extends BaseSetup {

    public SignInPage signInPage;
    public ExcelHelpers excel;

    @BeforeMethod
    public void loginTest() {
        //driver = getDriver();
        signInPage = new SignInPage();
        excel = new ExcelHelpers();
    }

    @Test(priority = 1)
    public void testLogin() {
        signInPage.logIn("frances.burns", "123456789");
    }

    @Test(priority = 2)
    public void testLoginWithUsernameInValid() throws Exception {
        excel.setExcelFile("src/test/resources/testdata/Book1.xlsx", "Sheet1");
        /*signInPage.loginWithUsernameInValid("admin012356", "frances.burns");*/
        signInPage.loginWithUsernameInValid(excel.getCellData("username", 1), excel.getCellData("password", 1) );
    }

    @Test(priority = 3)
    public void testLoginWithPasswordInValid() {
        signInPage.loginWithPasswordInValid("frances.burns", "123456789");

    }

    @Test(priority = 4)
    public void testForgotPassword() {
        signInPage.resetPassword("client01@mailinator.com");

    }

}
