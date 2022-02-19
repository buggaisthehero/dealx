package pageObjects;

import enumerables.Enums.LocatorType;
import helperObjects.TestBase;
import utilities.SeleniumDriver;

public class LandingPage extends TestBase{

	public LandingPage(SeleniumDriver seleniumDriver)
    {
    	super(seleniumDriver);
    }
	private String unitPriceStr;
	private String searchInputFieldID()
    {
    	return "search_query_top";
    }
	private String searchButtonNameID()
    {
    	return "submit_search";
    }
    private String itemsGridXpath(){return "//*[@id='center_column']/ul/li";}

	private String  womenMenuItemXpath(){return "//a[@title=\"Women\"]";}
	private String tshirtsSubMenuItemXpath(){return "(//a[@title=\"T-shirts\"])[1]";}
	private String itemXpath(){return "//div[@class=\"product-image-container\"]";}
	private String unitPriceLabelXpath(){return "our_price_display";}
	private String totaLabelXpath(){return "//span[@class=\"ajax_block_cart_total\"]";}

	private String quantityInputID(){return "quantity_wanted";}
	private String addToCartButtonID(){return "add_to_cart";}

	public  void navigate()  {
		seleniumDriver.navigateToPage(appConfig.getSelectedEnvironment().urlUnderTest);
	}

	public String getUnitPrice(){
		return unitPriceStr;
	}

	public String getTotalPrice(){
		return seleniumDriver.getTextFromElement(LocatorType.XPATH, totaLabelXpath());
	}

	public LandingPage search(String search_text)
	{
		this.navigate();
		seleniumDriver.enterText(LocatorType.ID, searchInputFieldID(), search_text);
		seleniumDriver.clickElement(LocatorType.NAME, searchButtonNameID());
		return new LandingPage(seleniumDriver);
	}

	public int getItemsCount(){
		return seleniumDriver.returnNUmberOfElements(LocatorType.XPATH, itemsGridXpath());
	}

	public LandingPage addItemsToCart(int qty) {
		seleniumDriver.hoverOverAndClick(LocatorType.XPATH, womenMenuItemXpath(), tshirtsSubMenuItemXpath());
		seleniumDriver.clickElement(LocatorType.XPATH, itemXpath());
		unitPriceStr = seleniumDriver.getTextFromElement(LocatorType.ID, unitPriceLabelXpath());
		seleniumDriver.enterText(LocatorType.ID, quantityInputID(), String.valueOf(qty));
		seleniumDriver.clickElement(LocatorType.XPATH, addToCartButtonID());

		return new LandingPage(seleniumDriver);
	}
    
    
}
