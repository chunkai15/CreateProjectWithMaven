package projects.crm.com.web.ecommerce.testcases;

import org.testng.annotations.Test;
import projects.crm.com.common.BaseSetup;
import projects.crm.com.web.ecommerce.pages.*;

public class EndToEndTest extends BaseSetup {

    private SignInPage signInPage;
    private HomePage homePage;
    private ProductOverviewPage productOverviewPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Test(priority = 1)
    public void addItemToCart() throws InterruptedException {
        signInPage = new SignInPage();
        homePage = signInPage.logIn("user@gmail.com", "1111");
        productOverviewPage = homePage.openPOP();
        productDetailPage = productOverviewPage.openPDP();
        cartPage = productDetailPage.addToCart();
        cartPage.openCart();
        checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.proceedToChecout();
    }

    /*@Test(priority = 2)
    public void goToPOP()  {
        productOverviewPage = homePage.openPOP();
    }

    @Test(priority = 3)
    public void goToPDP(){
        productDetailPage = productOverviewPage.openPDP();
    }

    @Test(priority = 4)
    public void goToCart(){
        cartPage = productDetailPage.addToCart();
    }

    @Test(priority = 5)
    public void openCart(){
        cartPage.openCart();
    }*/

}
