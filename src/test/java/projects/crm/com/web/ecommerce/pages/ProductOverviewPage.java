package projects.crm.com.web.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import projects.crm.com.driver.DriverManager;
import projects.crm.com.keyword.WebUI;

import java.util.List;

public class ProductOverviewPage {

    private By changeGrid = By.xpath("//body/form[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]");

    public ProductOverviewPage(){}

    public ProductDetailPage openPDP() throws InterruptedException {
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.clickElementWithJs(changeGrid);
        /*JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("arguments[0].click();", changeGrid);*/
        List<WebElement> listOfProduct = DriverManager.getDriver().findElements(By.xpath("/html[1]/body[1]/form[1]/section[1]/div[1]/div[1]/div[2]/div[2]"));
        int total = listOfProduct.size();
        System.out.println("list of products: " +total);
        Thread.sleep(3000);
        listOfProduct.get(0).click();

        return new ProductDetailPage();
    }
}
