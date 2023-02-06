package projects.crm.com.web.crm.pages;

import projects.crm.com.driver.DriverManager;
import projects.crm.com.keyword.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddProjectPage {
    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By inputTitle = By.xpath("//input[@placeholder='Title']");
    private By dropDownClient = By.xpath("//span[@id='select2-client_id-container']");
    //nếu không chọn được element thì lấy vùng bao phủ rộng ra, rồi chọn ngược vào trong
    /*private By inputClient = By.xpath("//div[@id='select2-drop']//input");*/
    private By inputClient = By.xpath("//span[@class='select2-search select2-search--dropdown']//input[@role='searchbox']");
    private By inputDescription = By.xpath("//textarea[@id='description']");
    private By inputStartDate = By.xpath("//input[@placeholder='Start Date']");
    private By inputDeadline = By.xpath("//input[@placeholder='End Date']");
    private By summary = By.xpath("//textarea[@id='summary']");
    private By inputPrice = By.xpath("//input[@id='price']");
    private By inputLabel = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[8]/div[1]/div[1]/div[1]/ul[1]/li[1]/input[1]");
    private By saveButton = By.xpath("//div[@class='card-footer text-right']//button[@type='submit']");
    private By searchProject = By.xpath("//input[@placeholder='Search']");
    private By titleProject = By.xpath("//a[contains(text(),'Demo Functional Testing')]");

    public AddProjectPage(){
    }

    public void addProjectPage() throws InterruptedException {
        WebUI.waitForPageLoaded();
        WebUI.setText(inputTitle, "Demo Functional Testing");
        WebUI.clickElement(dropDownClient);
        WebUI.setText(inputClient, "Cedric Kelly");
        Actions actions = new Actions(DriverManager.getDriver());
        actions.sendKeys(Keys.ENTER).build().perform();
        /*WebUI.setText(inputDescription, "This is Demo");*/
        WebUI.setText(inputStartDate, "2022-02-19");
        WebUI.setText(inputDeadline, "2022-02-21");
        /*WebUI.setText(inputPrice, "1000");
        WebUI.setText(inputLabel, "Learning");*//*
        actions.sendKeys(Keys.ENTER).build().perform();*/
        WebUI.clickElement(saveButton);
        WebUI.setText(searchProject, "Demo Functional Testing");
        //String textPR = driver.findElement(titleProject).getText();
        //Assert.assertEquals(textPR.toString(), "Demo Functional Testing");

    }
}
