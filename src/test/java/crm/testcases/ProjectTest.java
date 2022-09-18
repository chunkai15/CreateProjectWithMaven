package crm.testcases;

import crm.base.BaseSetup;
import crm.common.helpers.ExcelHelpers;
import crm.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectTest {
    private WebDriver driver;
    private SignInPage signInPage;
    private AddProjectPage addProjectPage;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private ExcelHelpers excel;

    @BeforeClass
    public void setUp() {
        //driver = getDriver();
        driver = new BaseSetup().setupDriver("chrome");
        signInPage = new SignInPage(driver);
        addProjectPage = new AddProjectPage(driver);
        excel = new ExcelHelpers();
    }

    @Test(priority = 1)
    public void login(){
        driver.get(new BaseSetup().getUrl());
        dashboardPage = signInPage.login("admin@mailinator.com", "123456");
    }

    @Test(priority = 2)
    public void openMyInfo(){
        projectPage = dashboardPage.OpenProjectPage();
    }

    @Test(priority = 3)
    public void addProject() throws InterruptedException {
        addProjectPage = projectPage.addProject();

    }

    @Test(priority = 4)
    public void addProjectInfo() throws InterruptedException {
        addProjectPage.addProjectPage();
    }

    @Test(priority = 5)
    public void searchProject() throws InterruptedException {
        projectPage.enterSearchValue("Demo Functional Testing");
        projectPage.checkSearchTableByColumn(2, "Demo Functional Testing");
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
