package crm.pages;

import crm.common.helpers.ValidateHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddProjectPage {
    private WebDriver driver;
    private ValidateHelpers validateHelper;

    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By inputTitle = By.xpath("//input[@id='title']");
    private By dropDownClient = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]");
    //nếu không chọn được element thì lấy vùng bao phủ rộng ra, rồi chọn ngược vào trong
    private By inputClient = By.xpath("//div[@id='select2-drop']//input");
    private By inputDescription = By.xpath("//textarea[@id='description']");
    private By inputStartDate = By.xpath("//input[@id='start_date']");
    private By inputDeadline = By.xpath("//input[@id='deadline']");
    private By inputPrice = By.xpath("//input[@id='price']");
    private By inputLabel = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[8]/div[1]/div[1]/div[1]/ul[1]/li[1]/input[1]");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By searchProject = By.xpath("//input[@placeholder='Search']");
    private By titleProject = By.xpath("//a[contains(text(),'Demo Functional Testing')]");

    public AddProjectPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelpers(driver);
    }

    public void addProjectPage() throws InterruptedException {
        validateHelper.waitForPageLoaded();
        validateHelper.setText(inputTitle, "Demo Functional Testing");
        validateHelper.clickElement(dropDownClient);
        validateHelper.setText(inputClient, "Client Project");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        validateHelper.setText(inputDescription, "This is Demo");
        validateHelper.setText(inputStartDate, "2022-02-19");
        validateHelper.setText(inputDeadline, "2022-02-21");
        validateHelper.setText(inputPrice, "1000");
        validateHelper.setText(inputLabel, "Learning");
        actions.sendKeys(Keys.ENTER).build().perform();
        validateHelper.clickElement(saveButton);
        validateHelper.setText(searchProject, "Demo Functional Testing");
        String textPR = driver.findElement(titleProject).getText();
        Assert.assertEquals(textPR.toString(), "Demo Functional Testing");

    }
}
