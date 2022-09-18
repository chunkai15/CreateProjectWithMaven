package crm.common.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValidateHelpers {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    private Select select;
    private JavascriptExecutor js;
    private final int timeoutWait = 10;
    private final int timeoutWaitForPageLoaded = 20;

    public ValidateHelpers(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeoutWait);
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public String getPageTitle()
    {
        waitForPageLoaded();
        String title = driver.getTitle();
        System.out.println("Page title:" + driver.getTitle());
        return title;
    }

    public boolean verifyPageTitle(String pageTitle)
    {
        waitForPageLoaded();
        return getPageTitle().equals(pageTitle);
    }

    public boolean verifyPageUrl(String pageUrl)
    {
        System.out.println("Current Url:" + driver.getCurrentUrl());
        return driver.getCurrentUrl().contains(pageUrl);
    }

    public boolean verifyPageText(By element, String textValue)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue);
    }

    public boolean verifyMessage(By element, String textValue)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue);
    }

    public void setText(By element, String text)
    {
        waitForPageLoaded();
        WebElement elementWaited = wait.until(ExpectedConditions.elementToBeClickable(element));
        elementWaited.click();
        elementWaited.clear();
        elementWaited.sendKeys(text);
    }

    public void clickElement(By element)
    {
        waitForPageLoaded();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void clickElementJs(By element)
    {
        waitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", driver.findElement(element));

    }

    public void rightClickElement(By element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.contextClick().build().perform();
    }

    //Handle dropdown
    public void selectOptionByText(By element, String text)
    {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public  void selectOptionByValue(By element, String value)
    {
        select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void verifyOptionTotal(By element, int total)
    {
        select = new Select(driver.findElement(element));
        Assert.assertEquals(total ,select.getOptions().size());
    }

    public void waitForPageLoaded() {
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            wait = new WebDriverWait(driver, 30 );
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }
}
