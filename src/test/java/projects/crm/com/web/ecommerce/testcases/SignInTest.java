package projects.crm.com.web.ecommerce.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projects.crm.com.common.BaseSetup;
import projects.crm.com.web.ecommerce.pages.SignInPage;

public class SignInTest extends BaseSetup {

    public SignInPage signInPage;

    @BeforeMethod
    public void loginTest() {
        //driver = getDriver();
        signInPage = new SignInPage();
    }

    @Test(priority = 1)
    public void testLogin() {
        signInPage.logIn("user@gmail.com", "1111");
    }
}
