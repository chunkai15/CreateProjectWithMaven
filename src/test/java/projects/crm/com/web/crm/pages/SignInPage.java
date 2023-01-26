package projects.crm.com.web.crm.pages;

import projects.crm.com.keyword.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    //Login
    private String pageText = "Welcome to HRM System";
    private By inputUsername = By.xpath("//input[@id='iusername']");
    private By inputPassword = By.xpath("//input[@id='ipassword']");
    private By buttonSignin = By.xpath("//button[@type='submit']");
    private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password?']");

    //Forgot password
    private By pageTextForgotPassword = By.xpath("//h4[normalize-space()='Reset your password']");
    private By inputEmailForgotPassword = By.xpath("//input[@placeholder='Email address']");
    private By buttonReset = By.xpath("//button[@type='submit']");
    private By linkClickHere = By.xpath("//a[normalize-space()='Click here']");
    private By alertMessage = By.xpath("//div[@class='toast-message']");

    public SignInPage()
    {
    }

    public DashboardPage logIn(String email, String password){
        WebUI.openURL("https://app.hrsale.com/erp/login");
        WebUI.waitForPageLoaded();
        //Assert.assertTrue(WebUI.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
       // Assert.assertTrue(WebUI.verifyPageUrl(pageUrl), "URL chưa đúng");
        WebUI.setText(inputUsername, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonSignin);

        return new DashboardPage();
    }

    public void loginWithUsernameInValid(String username, String password) {
        WebUI.openURL("https://app.hrsale.com/erp/login");
        WebUI.setText(inputUsername, username);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonSignin);
        //Xử lý Assert
        /*boolean checkAlertError = WebUI.checkElementExist(alertMessage);*/
        //Assert.assertTrue(checkAlertError, "Fail. Error alert not display.");
    }

    public void loginWithPasswordInValid(String username, String password) {
        WebUI.openURL("https://app.hrsale.com/erp/login");
        WebUI.setText(inputUsername, username);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonSignin);
        //Xử lý Assert
        /*boolean checkAlertError = WebUI.checkElementExist(alertMessage);*/
        /*Assert.assertTrue(checkAlertError, "Fail. Error alert not display.");*/

    }

    public void resetPassword(String emailForgot) {
        WebUI.openURL("https://app.hrsale.com/erp/login");
        WebUI.clickElement(linkForgotPassword);
        WebUI.setText(inputEmailForgotPassword, emailForgot);
        WebUI.clickElement(buttonReset);
        //Assert cái message hiển thị thành công (tồn tại)
        WebUI.clickElement(linkClickHere);
    }

}
