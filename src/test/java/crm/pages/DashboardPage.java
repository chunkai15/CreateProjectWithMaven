package crm.pages;

import crm.common.helpers.ValidateHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {

    private WebDriver driver;
    private ValidateHelpers validateHelper;

    private String pageUrl = "/login";
    private String pageText = "Dashboard";
    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By projectMenu = By.xpath("//span[normalize-space()='Projects']");

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelpers(driver);
    }

    public ProjectPage OpenProjectPage(){
        validateHelper.waitForPageLoaded();
        //Assert.assertTrue(validateHelper.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
        validateHelper.clickElement(projectMenu);

        return new ProjectPage(driver);
    }
}
