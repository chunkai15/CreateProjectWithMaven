package projects.crm.com.web.crm.pages;

import projects.crm.com.keyword.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;
    private WebUI validateHelper;

    private String pageUrl = "/login";
    private String pageText = "Dashboard";
    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By projectMenu = By.xpath("//span[normalize-space()='Projects']");

    public DashboardPage(){
    }

    public ProjectPage OpenProjectPage(){
        WebUI.waitForPageLoaded();
        //Assert.assertTrue(validateHelper.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
        WebUI.clickElement(projectMenu);

        return new ProjectPage();
    }
}
