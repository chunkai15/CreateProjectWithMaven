package crm.pages;

import crm.common.helpers.ValidateHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignInPage {

    private WebDriver driver;
    private ValidateHelpers validateHelper;

    private String pageUrl = "/signin";
    private String pageText = "LOGIN Panel";
    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By inputEmail = By.xpath("//input[@id='iusername']");
    private By inputPassword = By.xpath("//input[@id='ipassword']");
    private By loginBtn = By.xpath("//button[@type='submit']");

    public SignInPage(WebDriver driver)
    {
        this.driver = driver;
        validateHelper = new ValidateHelpers(driver);
    }

    public DashboardPage login(String email, String password)
    {
        validateHelper.waitForPageLoaded();
        //Assert.assertTrue(validateHelper.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
       // Assert.assertTrue(validateHelper.verifyPageUrl(pageUrl), "URL chưa đúng");
        validateHelper.setText(inputEmail, email);
        validateHelper.setText(inputPassword, password);
        validateHelper.clickElement(loginBtn);

        return new DashboardPage(driver);
    }

}
