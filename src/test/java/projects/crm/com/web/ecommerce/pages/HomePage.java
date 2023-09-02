package projects.crm.com.web.ecommerce.pages;

import org.openqa.selenium.By;
import projects.crm.com.keyword.WebUI;

public class HomePage {

    private By buttonShopNow = By.xpath("//section[@class='small-banner section']//div[@class='row']//div[1]//div[1]//div[1]//a[1]");

    public HomePage(){}

    public ProductOverviewPage openPOP()   {
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.clickElementWithJs(buttonShopNow);

        return new ProductOverviewPage();
    }
}
