package tests;

import helperObjects.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage;



public class TC2 extends TestBase {
    @BeforeTest
    public void prepareTest(){
        this.prepare();
    }

    @Test()
    public class testetsng {
       String searchText = new String("Blouses,Casual Dresses,T-shirts");
       String[] searchArray = searchText.split(",");

        @Test()
        public void test() {
            LandingPage landingPage = new LandingPage(seleniumDriver);
            landingPage.navigate();
            String searchText;

            for (int index = 0; index < searchArray.length; index++) {
                searchText = searchArray[index];
                landingPage.search(searchText);
                Assert.assertTrue(landingPage.getItemsCount() > 0);
            }

        }
    }

    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
}
