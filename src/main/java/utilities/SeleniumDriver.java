package utilities;

import enumerables.Enums.BrowserTypes;
import enumerables.Enums.LocatorType;

import helperObjects.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.notification.StoppedByUserException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver extends TestBase
{
    private  WebDriver webDriver;

    public SeleniumDriver(BrowserTypes browserType)
    {
        switch(browserType)
        {
            case Chrome:
                WebDriverManager.chromedriver().setup();
                this.webDriver = new ChromeDriver();
                break;
            case FireFox:
                WebDriverManager.firefoxdriver().setup();
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setCapability("marionette", true);
                this.webDriver = new FirefoxDriver(cap);
                break;
            default:

        }

        this.webDriver.manage().timeouts().implicitlyWait(appConfig.getWaitTimeout(), TimeUnit.SECONDS);
        this.webDriver.manage().timeouts().pageLoadTimeout(appConfig.getPageLoadTimeout(), TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();
    }

    public boolean navigateToPage(String url)
    {
        try
        {
            webDriver.navigate().to(url);
            return true;
        }
        catch(StaleElementReferenceException | IllegalStateException | NoSuchElementException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    public  String getTextFromElement(LocatorType locatorType, String value)
    {
        try {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            String text = element.getText();
            return text;
        }
        catch(Exception e){
            throw e;
        }
    }

    public boolean enterText(LocatorType locatorType, String value, String text)
    {
        try
        {
            By locator = LocatorValue(locatorType, value);

            WebElement element = (new WebDriverWait(webDriver, 10))
                    .until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));

            element.clear();
            element.sendKeys(text);

            return true;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean selectOption(LocatorType locatorType, String value, String text)
    {
        try
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);
            Select dropdown = new Select(element);
            element.click();
            dropdown.selectByVisibleText(text);
            return true;
        }

        catch(Exception e)
        {
            throw e;
        }
    }

    public  int returnNUmberOfElements(LocatorType locatorType, String value)
    {
        try
        {
            int size = 0;
            By locator = LocatorValue(locatorType, value);
            List<WebElement> element = webDriver.findElements(locator);
            size = element.size();
            return size;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public boolean clickElement(LocatorType locatorType, String value)
    {
        try
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = (new WebDriverWait(webDriver, 10))
                    .until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
            element.click();
            return true;
        }
        catch (WebDriverException e)
        {
            throw e;
        }

    }

    public boolean hoverOverAndClick(LocatorType locatorType, String menu, String subMenu) {
        try
        {
            Actions a = new Actions(webDriver);
            By locator = LocatorValue(locatorType, menu);
            WebElement element = (new WebDriverWait(webDriver, 10))
                    .until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
            a.moveToElement(element).build().perform();

            locator = LocatorValue(locatorType, subMenu);
            element = webDriver.findElement(locator);


            element.click();


            return true;
        }
        catch (WebDriverException e)
        {
            throw e;
        }
    }

    public static By LocatorValue(LocatorType locatorType, String value)
    {
        By by;
        switch (locatorType)
        {
            case ID:
                by = By.id(value);
                break;
            case NAME:
                by = By.name(value);
                break;
            case XPATH:
                by = By.xpath(value);
                break;
            case CSS:
                by = By.cssSelector(value);
                break;
            case LINK_TEXT:
                by = By.linkText(value);
                break;
            case PARTIAL_LINK_TEXT:
                by = By.partialLinkText(value);
                break;
            case CLASS_NAME:
                by = By.className(value);
                break;
            default:
                by = null;
                break;
        }
        return by;
    }

    public  String getPageTitle()
    {
        return this.webDriver.getTitle();
    }

    public  WebDriver getWebDriver()
    {
        try
        {
            return webDriver;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public void closeBrowser()
    {
        if(webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }



}

