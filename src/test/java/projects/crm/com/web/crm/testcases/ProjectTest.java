package projects.crm.com.web.crm.testcases;

import io.qameta.allure.Step;
import org.testng.annotations.Listeners;
import projects.crm.com.helpers.ExcelHelpers;
import projects.crm.com.common.BaseSetup;
import projects.crm.com.web.crm.pages.AddProjectPage;
import projects.crm.com.web.crm.pages.DashboardPage;
import projects.crm.com.web.crm.pages.ProjectPage;
import projects.crm.com.web.crm.pages.SignInPage;
import projects.crm.com.web.crm.pages.*;
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
        signInPage = new SignInPage();
        addProjectPage = new AddProjectPage();
        excel = new ExcelHelpers();
    }

    @Test(priority = 1, description ="Login")
    @Step("Login to hrm page")
    public void login(){
        /*driver.get(new BaseSetup().getUrl());*/
        dashboardPage = signInPage.logIn("joe.larson", "joe.larson");
    }

    @Test(priority = 2, description ="Login 1")
    @Step("Open project page")
    public void openMyInfo(){
        projectPage = dashboardPage.OpenProjectPage();
    }

    @Test(priority = 3, description ="Login 2")
    @Step("Click Add project")
    public void addProject() throws InterruptedException {
        addProjectPage = projectPage.addProject();

    }

    @Test(priority = 4)
    @Step("Add project")
    public void addProjectInfo() throws InterruptedException {
        addProjectPage.addProjectPage();
    }

    @Test(priority = 5)
    @Step("Search project")
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
