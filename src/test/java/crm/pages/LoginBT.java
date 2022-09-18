package crm.pages;

import crm.common.helpers.ValidateHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginBT {

    private WebDriver driver;
    private ValidateHelpers validateHelper;

    private String pageUrl = "/signin";
    private String pageText = "LOGIN Panel";
    private String pageTitle = "Project Manager | Anh Tester Demo";
    private String faMessage = "Authentication failed";

    private By inputUsername= By.xpath("//input[@id='username']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By loginBtn = By.xpath("//button[normalize-space()='Login']");
    //private By loginMessage = By.xpath("//div[@role='alert']");

    public LoginBT(WebDriver driver)
    {
        this.driver = driver;
        validateHelper = new ValidateHelpers(driver);
    }

    public void login(String email, String password) throws InterruptedException {
        validateHelper.waitForPageLoaded();
        //Assert.assertTrue(validateHelper.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
        //Assert.assertTrue(validateHelper.verifyPageUrl(pageUrl), "URL chưa đúng");

        validateHelper.setText(inputUsername, email);
        validateHelper.setText(inputPassword, password);
        validateHelper.clickElement(loginBtn);

        /*int sizeIFrame = driver.findElements(By.tagName("iframe")).size();
        System.out.println(sizeIFrame);

        Thread.sleep(1000);
        //Chuyen den frame theo name/id
        driver.switchTo().frame("nr-ext-rsicon");
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Username hoặc Password đã nhập sai!')]"));
        System.out.println(element.getText());
        driver.switchTo().defaultContent();*/

    }

}
