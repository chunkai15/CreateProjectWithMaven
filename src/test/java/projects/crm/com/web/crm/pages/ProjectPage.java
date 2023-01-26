package projects.crm.com.web.crm.pages;

import projects.crm.com.driver.DriverManager;
import projects.crm.com.keyword.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProjectPage {

    private String pageTitle = "CRM Project Manager | Anh Tester Demo";

    private By addButton = By.xpath("//body/div[2]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/a[2]");
    private By projectSearchInput = By.xpath("//input[@class='form-control form-control-sm']");

    public ProjectPage(){
    }

   public AddProjectPage addProject() throws InterruptedException {
       WebUI.waitForPageLoaded();
       //Assert.assertTrue(validateHelper.verifyPageTitle(pageTitle), "Tiêu đề chưa đúng");
       WebUI.clickElement(addButton);

        return new AddProjectPage();
   }

   public void enterSearchValue(String value){
       WebUI.setText(projectSearchInput, value);
   }

   public void checkSearchTableByColumn(int column, String value){
        //xác định số dòng của table sau khi search
        List<WebElement> rowTable = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = rowTable.size();

        for (int i = 1; i <= rowTotal; i++){
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr["+ i +"]//td["+column+"]"));

            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số "+i+" không chứa giá trị tìm kiếm");
        }
   }
}
