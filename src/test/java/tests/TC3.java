package tests;

import enumerables.Enums;
import helperObjects.TestBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class TC3 extends TestBase {
    @BeforeTest
    public void prepareTest(){
        this.prepare();
    }

    @Test()
    public class testetsng {

        public void test() {
            LandingPage landingPage = new LandingPage(seleniumDriver);
            landingPage.navigate();
            String searchText;

            ArrayList searchArray= readJsonFile("src/inputfile/search.json");

            for (int index = 0; index < searchArray.size(); index++) {
                searchText = searchArray.get(index).toString();
                landingPage.search(searchText);
                Assert.assertTrue(landingPage.getItemsCount() > 0);
            }

        }

        public ArrayList readJsonFile(String fileLocation)
        {
            JSONParser parser = new JSONParser();
            ArrayList<Object> searchArray = new ArrayList<>();
            try
            {
                Object obj = parser.parse(new FileReader(fileLocation));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray searchList = (JSONArray) jsonObject.get("SearchItems");

                for (Object o : searchList) {
                    JSONObject jsonLineItem = (JSONObject) o;
                    searchArray.add(jsonLineItem.get("item"));
                }
                return searchArray;
            }
            catch (IOException | ParseException e)
            {
                return null;
            }
        }
    }



    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
}
