package crm.pages;

import crm.common.helpers.ValidateHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;

public class ProjectPage {

    private WebDriver driver;
    private ValidateHelpers validateHelper;
    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By addButton = By.xpath("//a[@title='Add project']");
    private By projectSearchInput = By.xpath("//input[@placeholder='Search']");

    public ProjectPage(WebDriver driver){
        this.driver = driver;
        validateHelper = new ValidateHelpers(driver);
    }

   public AddProjectPage addProject() throws InterruptedException {
        validateHelper.waitForPageLoaded();
       //Assert.assertTrue(validateHelper.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
        validateHelper.clickElement(addButton);

        return new AddProjectPage(driver);
   }

   public void enterSearchValue(String value){
        validateHelper.setText(projectSearchInput, value);
   }

   public void checkSearchTableByColumn(int column, String value){
        //xác định số dòng của table sau khi search
        List<WebElement> rowTable = driver.findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = rowTable.size();

        for (int i = 1; i <= rowTotal; i++){
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr["+ i +"]//td["+column+"]"));

            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số "+i+" không chứa giá trị tìm kiếm");
        }
   }
}
