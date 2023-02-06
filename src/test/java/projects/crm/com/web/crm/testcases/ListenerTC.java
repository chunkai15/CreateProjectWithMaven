package projects.crm.com.web.crm.testcases;

import projects.crm.com.common.BaseSetup;
import projects.crm.com.driver.DriverManager;
import projects.crm.com.listeners.TestListener;
import projects.crm.com.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.SkipException;

@Listeners(TestListener.class)
public class ListenerTC {
    private WebDriver driver;
    @BeforeClass
    public void setupDriver() {

       // driver = new BaseSetup().setupDriver(PropertiesHelper.getValue("browser"));
    }

    @Test(priority = 1) //Success Test
    public void gotoPage() {
        DriverManager.getDriver().get("https://anhtester.com");
    }

    @Test(priority = 2) //Failed Test
    public void checkTitle() {
        String expectedTitle = "Anh Tester";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, expectedTitle, "Title of the website do not match");
    }

    @Test(priority = 3)  //Skip Test
    public void skipTest() {
        throw new SkipException("Skipping The Test Method ");
    }

    /*int count = 0;

    @Test(invocationCount = 5, successPercentage = 50)
    public void kiemTraChanLe() {
        count++;
        System.out.println("Số lần chạy: " + count);

        if (count % 2 == 0) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }*/

    @AfterClass
    public void closeDriver() {
        DriverManager.quit();
    }
}