package projects.crm.com.web.ecommerce.pages;

import org.openqa.selenium.By;
import projects.crm.com.keyword.WebUI;

public class SignInPage {
    //Login
    private By linkLogin = By.xpath("//a[normalize-space()='Login /']");
    private By inputEmail = By.xpath("//input[@name='email']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");

    public SignInPage(){
    }

    public HomePage logIn(String email, String password){
        WebUI.openURL("http://127.0.0.1:8000/user/login");
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);

        return new HomePage();
    }
}
