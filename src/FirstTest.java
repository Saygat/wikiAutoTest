import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "Users/aleksandrfilimonov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {


        waitForElementAndClickById(
                "org.wikipedia:id/fragment_onboarding_skip_button",
                "Cannot find search input",
                5);

//        WebElement skipButton = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
//        skipButton.click();
//        WebElement element_to_init_search = driver.findElementById("org.wikipedia:id/search_container");
//        element_to_init_search.click();
//        WebElement element_to_enter_search_line = driver.findElementByXPath("//*[contains(@text, 'Поиск по Википедии')]");
//        WebElement element_to_enter_search_line = driver.findElementById("org.wikipedia:id/search_src_text");//        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
//                "//*[contains(@text, 'Поиск по Википедии')]",
//                "Cannot find search input");
//
//        element_to_enter_search_line.sendKeys("Java");

        waitForElementAndClickById(
                "org.wikipedia:id/search_container",
                "Cannot find search edit text",
                5);



        waitForElementAndSendKeysByXpath(
                "//*[contains(@text, 'Поиск по Википедии')]",
                "Java",
                "Cannot find search edit text to enter message",
                5);

        List<WebElement> searchResult = driver.findElements(By.id("org.wikipedia:id/search_results_list"));
        WebElement scrollView = searchResult.get(0);
        List<WebElement> resultsInList = scrollView.findElements(By.className("android.view.ViewGroup"));
        resultsInList.get(0).click();

    }

    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentById(String id, String error_message) {
        return waitForElementPresentById(id, error_message, 5);
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message) {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement waitForElementAndClickById(String id, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClickByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeysById(String id, String value, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndSendKeysByXpath(String xpath, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}