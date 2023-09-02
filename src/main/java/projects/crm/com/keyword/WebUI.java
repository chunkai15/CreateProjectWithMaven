package projects.crm.com.keyword;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import projects.crm.com.constants.FrameworkConstants;
import projects.crm.com.driver.DriverManager;
import projects.crm.com.helpers.Helpers;
import projects.crm.com.report.AllureManager;
import projects.crm.com.report.ExtentReportManager;
import projects.crm.com.report.ExtentTestManager;
import projects.crm.com.utils.DateUtils;
import projects.crm.com.utils.LogUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;
import static projects.crm.com.constants.FrameworkConstants.*;

public class WebUI {
    private final static long TIMEOUT = 10;
    private final static double STEP_TIME = 0;
    private final static int PAGE_LOAD_TIMEOUT = 20;

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    @Step("Verify Equals: {0} and {1}")
    public static void verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        LogUtils.info("Verify equals: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, "Fail. Not match. '" + actual.toString() + "' != '" + expected.toString() + "'");
    }

    @Step("Verify Equals: {0} and {1}")
    public static void verifyEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        LogUtils.info("Verify equals: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "Verify equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static Boolean checkElementExist(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(2);
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            System.out.println("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("Open: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
        AllureManager.saveTextLog("Open: " + url);
        waitForPageLoaded();
    }

    @Step("Click element: {0}")
    public static void clickElement(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
        AllureManager.saveTextLog("Click element: " + by);
    }

    @Step("Click on the element by Javascript {0}")
    public static void clickElementWithJs(By by) {
        waitForElementPresent(by);
        //Scroll to element với Javascript Executor`
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        //Click with JS
        js.executeScript("arguments[0].click();", getWebElement(by));

        //LogUtils.info("Click on element with JS: " + by);
        LogUtils.info("Click on element with JS: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click on element with JS: " + by);
        AllureManager.saveTextLog("Click on element with JS: " + by);
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Click element {0} with timeout {1}")
    public static void clickElement(By by, long timeout) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
    }

    @Step("Set text {1} on {0}")
    public static void setText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        AllureManager.saveTextLog("Set text " + value + " on " + by.toString());
    }

    @Step("Set text on textbox and press key")
    public static void setText(By by, String value, Keys keys) {
        waitForElementVisible(by).sendKeys(value, keys);
        LogUtils.info("Set text " + value + " on " + by + " and press key " + keys.name());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Set text " + value + " on " + by + " and press key " + keys.name());
        }
        AllureManager.saveTextLog("Set text " + value + " on " + by + " and press key " + keys.name());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Set text on textbox and press key")
    public static void sendKeys(By by, Keys keys) {
        waitForElementVisible(by).sendKeys(keys);
        LogUtils.info("Press key " + keys.name() + " on element " + by);

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Press key " + keys.name() + " on element " + by);
        }
        AllureManager.saveTextLog("Press key " + keys.name() + " on element " + by);

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
    }

    @Step("Set text on textbox and press key")
    public static void sendKeys(Keys keys) {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.sendKeys(keys);
        LogUtils.info("Press key " + keys.name() + " on keyboard");

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Press key " + keys.name() + " on keyboard");
        }
        AllureManager.saveTextLog("Press key " + keys.name() + " on keyboard");

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
    }

    @Step("Clear value in textbox")
    public static void clearText(By by) {
        waitForElementVisible(by).clear();
        LogUtils.info("Clear text in textbox " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clear text in textbox " + by.toString());
        }
        AllureManager.saveTextLog("Clear text in textbox");
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Clear text in textbox with Ctrl A")
    public static void clearTextCtrlA(By by) {
        waitForElementVisible(by);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.click(getWebElement(by)).build().perform();
        //actions.moveToElement(getWebElement(by)).click().build();
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.sendKeys(Keys.DELETE).build().perform();

        LogUtils.info("Clear text in textbox " + by.toString());
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clear text in textbox " + by.toString());
        }
        AllureManager.saveTextLog("Clear text in textbox");
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Clear and Fill text on text box")
    public static void clearAndFillText(By by, String value) {
        Objects.requireNonNull(waitForElementVisible(by)).clear();
        Objects.requireNonNull(waitForElementVisible(by)).sendKeys(value);
        LogUtils.info("Clear and Fill " + value + " on " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clear and Fill " + value + " on " + by.toString());
        }
        AllureManager.saveTextLog("Clear and Fill " + value + " on " + by.toString());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Get text of element {0}")
    public static String getElementText(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getText();
        LogUtils.info("Get text: " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get text: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    public static void addScreenshotToReport(String screenshotName) {
        if (SCREENSHOT_ALL_STEPS.equals(YES)) {
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.addScreenShot(Helpers.makeSlug(screenshotName));
            }
            //CaptureHelpers.captureScreenshot(DriverManager.getDriver(), Helpers.makeSlug(screenshotName));
            AllureManager.takeScreenshotStep();
        }
    }

    //Handle checkbox and radio button

    @Step("Verify Element Checked {0}")
    public static boolean verifyElementChecked(By by) {
        waitForPageLoaded();

        boolean checked = getWebElement(by).isSelected();

        if (checked) {
            return true;
        } else {
            return false;
        }
    }

    @Step("Verify Element Checked {0}")
    public static boolean verifyElementChecked(By by, String message) {
        waitForPageLoaded();

        waitForElementVisible(by);

        boolean checked = getWebElement(by).isSelected();

        if (checked) {
            return true;
        } else {
            Assert.fail(message);
            return false;
        }
    }

    @Step("Verify element present {0}")
    public static boolean verifyElementPresent(By by) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            LogUtils.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify element present {0} with timeout {1} second")
    public static boolean verifyElementPresent(By by, int timeout) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            LogUtils.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify element present {0}")
    public static boolean verifyElementPresent(By by, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element does NOT present. " + e.getMessage());
                Assert.fail("The element does NOT present. " + e.getMessage());
            } else {
                LogUtils.error(message);
                Assert.fail(message);
            }

            return false;
        }
    }

    @Step("Verify element present {0} with timeout {1} second")
    public static boolean verifyElementPresent(By by, int timeout, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element does NOT present. " + e.getMessage());
                Assert.fail("The element does NOT present. " + e.getMessage());
            } else {
                LogUtils.error(message);
                Assert.fail(message);
            }

            return false;
        }
    }

    @Step("Verify element NOT present {0}")
    public static boolean verifyElementNotPresent(By by) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.error("The element presents. " + by);
            Assert.fail("The element presents. " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT present {0} with timeout {1} second")
    public static boolean verifyElementNotPresent(By by, int timeout) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.error("Element is present " + by);
            Assert.fail("The element presents. " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT present {0}")
    public static boolean verifyElementNotPresent(By by, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element presents. " + by);
                Assert.fail("The element presents. " + by);
            } else {
                LogUtils.error(message);
                Assert.fail(message + " " + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT present {0} with timeout {1} second")
    public static boolean verifyElementNotPresent(By by, int timeout, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element presents. " + by);
                Assert.fail("The element presents. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean isElementVisible(By by, long timeout) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element visible {0} with timeout {1} second")
    public static boolean verifyElementVisible(By by, long timeout) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("The element is not visible. " + e.getMessage());
            Assert.fail("The element is NOT visible. " + by);
            return false;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Verify element visible {0} with timeout {1} second")
    public static boolean verifyElementVisible(By by, int timeout, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Verify element NOT visible {0}")
    public static boolean verifyElementNotVisible(By by) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.error("FAILED. The element is visible " + by);
            Assert.fail("FAILED. The element is visible " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT visible {0} with timeout {1} second")
    public static boolean verifyElementNotVisible(By by, int timeout) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.error("FAILED. The element is visible " + by);
            Assert.fail("FAILED. The element is visible " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT visible {0}")
    public static boolean verifyElementNotVisible(By by, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("FAILED. The element is visible " + by);
                Assert.fail("FAILED. The element is visible " + by);
            } else {
                LogUtils.error(message + " " + by);
                Assert.fail(message + " " + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT visible {0} with timeout {1} second")
    public static boolean verifyElementNotVisible(By by, int timeout, String message) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("FAILED. The element is visible " + by);
                Assert.fail("FAILED. The element is visible " + by);
            } else {
                LogUtils.error(message + " " + by);
                Assert.fail(message + " " + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(By by) {
        waitForPageLoaded();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(By by) {
        waitForPageLoaded();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(WebElement element) {
        waitForPageLoaded();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(WebElement element) {
        waitForPageLoaded();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        LogUtils.info("Scroll to element " + element);
    }

    //Dropdown (Select Option)
    @Step("Select Option by Text {0}")
    public static void selectOptionByText(By by, String text) {
        waitForPageLoaded();
        Select select = new Select(getWebElement(by));
        select.selectByVisibleText(text);
    }

    @Step("Select Option by Value {0}")
    public static void selectOptionByValue(By by, String value) {
        waitForPageLoaded();
        Select select = new Select(getWebElement(by));
        select.selectByValue(value);
    }

    @Step("Select Option by Index {0}")
    public static void selectOptionByIndex(By by, int index) {
        waitForPageLoaded();
        Select select = new Select(getWebElement(by));
        select.selectByIndex(index);
    }

    @Step("Verify Option Total equals {0}")
    public static void verifyOptionTotal(By element, int total) {
        waitForPageLoaded();

        Select select = new Select(getWebElement(element));
        Assert.assertEquals(total, select.getOptions().size());
    }

    @Step("Verify Selected Option by Text {0}")
    public static boolean verifySelectedByText(By by, String text) {
        waitForPageLoaded();

        Select select = new Select(getWebElement(by));
        LogUtils.info("Verify Option Selected by text: " + select.getFirstSelectedOption().getText());
        return select.getFirstSelectedOption().getText().equals(text);
    }

    @Step("Verify Selected Option by Value {0}")
    public static boolean verifySelectedByValue(By by, String optionValue) {
        waitForPageLoaded();

        Select select = new Select(getWebElement(by));
        LogUtils.info("Verify Option Selected by value: " + select.getFirstSelectedOption().getAttribute("value"));
        return select.getFirstSelectedOption().getAttribute("value").equals(optionValue);
    }

    @Step("Verify Selected Option by Index {0}")
    public static boolean verifySelectedByIndex(By by, int index) {
        waitForPageLoaded();

        boolean res = false;
        Select select = new Select(getWebElement(by));
        int indexFirstOption = select.getOptions().indexOf(select.getFirstSelectedOption());
        LogUtils.info("The First Option selected by index: " + indexFirstOption);
        LogUtils.info("Expected index: " + index);
        if (indexFirstOption == index) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    //Wait for Element

    public static WebElement waitForElementVisible(By by, long timeOut) {
        waitForPageLoaded();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));

            boolean check = verifyElementVisible(by, timeOut);
            if (check) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElementToTop(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public static WebElement waitForElementVisible(By by) {
        waitForPageLoaded();
        waitForElementPresent(by);

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            boolean check = isElementVisible(by, 1);
            if (check) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElementToBottom(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());

        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.info("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    //Vài hàm bổ trợ nâng cao hơn

    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Hover on element {0}")
    public static boolean hoverOnElement(By by) {
        waitForPageLoaded();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            LogUtils.info("Hover on element " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Mouse hover on element {0}")
    public static boolean mouseHover(By by) {
        waitForPageLoaded();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            LogUtils.info("Mouse hover on element " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    /**
     * Wait for Page
     * Chờ đợi trang tải xong (Javascript) với thời gian thiết lập sẵn
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    /**
     * Chờ đợi JQuery tải xong với thời gian thiết lập sẵn
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            LogUtils.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    //Wait for Angular Load
    /**
     * Chờ đợi Angular tải xong với thời gian thiết lập sẵn
     */
    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) DriverManager.getDriver()).executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            LogUtils.info("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }

    }

}
