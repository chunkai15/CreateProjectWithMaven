package projects.crm.com.web.ecommerce.pages;

import org.openqa.selenium.By;
import projects.crm.com.keyword.WebUI;

public class ProductDetailPage {

    private By buttonAddToCart = By.xpath("//button[contains(text(),'Add to cart')]");

    public ProductDetailPage(){}

    public CartPage addToCart(){
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.clickElementWithJs(buttonAddToCart);

        return new CartPage();
    }
}
