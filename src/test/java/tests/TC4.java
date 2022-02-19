package tests;

import helperObjects.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.Login;


public class TC4 extends TestBase {
    final String email = "test@gmail.com";
    final String password = "xyz";

    @BeforeTest
    public void prepareTest(){
        this.prepare();
    }

    @Test()
    public class testetsng {
        @Test()
        public void test() {
            LandingPage landingPage = new LandingPage(seleniumDriver);
            Login login = new Login(seleniumDriver);

            landingPage.navigate();
            login.loginUser(email, password);

        }
    }

    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
}
