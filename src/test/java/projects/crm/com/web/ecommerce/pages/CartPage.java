package projects.crm.com.web.ecommerce.pages;

import org.openqa.selenium.By;
import projects.crm.com.keyword.WebUI;

public class CartPage {

    private By cartButton = By.xpath("//i[@class='ti-bag']");
    private By checkOutButton = By.xpath("//a[@class='btn'][normalize-space()='Checkout']");

    public CartPage(){}

    public void openCart(){
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.clickElementWithJs(cartButton);
    }

    public CheckoutPage goToCheckoutPage(){
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.clickElementWithJs(checkOutButton);

        return new CheckoutPage();
    }
}
