package tests;

import helperObjects.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.Login;


public class TC5 extends TestBase {

    @BeforeTest
    public void prepareTest(){
        this.prepare();
    }

    @Test()
    public class testetsng {
        @Test()
        public void test(){
            int quantity = 2;
            LandingPage landingPage = new LandingPage(seleniumDriver);
            landingPage.navigate();
            landingPage.addItemsToCart(quantity);
            String unitPrice = landingPage.getUnitPrice().replace("$","");

            double unitPriceDigit = Double.parseDouble(unitPrice);
            String actualTotal = landingPage.getTotalPrice();
            String expectedTotal = String.format("%S%S", "$", calculateTotalPrice(unitPriceDigit, quantity));

            Assert.assertEquals(actualTotal, expectedTotal);

        }
    }

    private  String calculateTotalPrice(double unitPrice, int qty){
        double totalPrice = unitPrice * qty;
        String totalPriceStr = String.format("%.2f", totalPrice);

        return totalPriceStr;
    }

    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
}
