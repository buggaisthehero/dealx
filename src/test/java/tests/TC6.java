package tests;

import helperObjects.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;


public class TC6 extends TestBase {

    @BeforeTest
    public void prepareTest(){
        this.prepare();
    }

    @Test()
    public class testetsng {
        @Test()
        public void test(){
            int womanMenuIndex = 1;
            LandingPage landingPage = new LandingPage(seleniumDriver);
            landingPage.navigate();
            landingPage.selectMenuAndSubMenu(womanMenuIndex);
        }
    }

    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
}
