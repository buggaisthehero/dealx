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
	private String totalPriceStr;
	private String searchInputFieldID()
    {
    	return "search_query_top";
    }
	private String searchButtonNameID()
    {
    	return "submit_search";
    }
    private String itemsGridXpath(){return "//*[@id='center_column']/ul/li";}
	private String  menuItemXpath(){return "(//div[@id=\"block_top_menu\"]/ul/li)[1]";}
	private String  menuItemGenericXpath(int index){return "(//div[@id=\"block_top_menu\"]/ul/li)["+index+"]";}
	private String  subMenuItemsXpath(){return "";}
	private String tshirtsSubMenuItemXpath(){return "(//a[@title=\"T-shirts\"])[1]";}
	private String itemXpath(){return "//div[@class=\"product-image-container\"]";}
	private String unitPriceLabelID(){return "our_price_display";}
	private String totaLabelXpath(){return "//span[@class=\"ajax_block_products_total\"]";}

	private String quantityInputID(){return "quantity_wanted";}
	private String addToCartButtonID(){return "add_to_cart";}

	private String popUpIframeXpath(){return "//iframe[@class=\"fancybox-iframe\"]";}

	public  void navigate()  {
		seleniumDriver.navigateToPage(appConfig.getSelectedEnvironment().urlUnderTest);
	}

	public String getUnitPrice(){
		return unitPriceStr;
	}

	public String getTotalPrice(){
		return totalPriceStr;
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
		seleniumDriver.hoverOverMenuAndClickSubItem(LocatorType.XPATH, menuItemXpath(), tshirtsSubMenuItemXpath());
		seleniumDriver.clickElement(LocatorType.XPATH, itemXpath());
		seleniumDriver.switchToIframe(LocatorType.XPATH, popUpIframeXpath());
		unitPriceStr = seleniumDriver.getTextFromElement(LocatorType.ID, unitPriceLabelID());
		seleniumDriver.enterText(LocatorType.ID, quantityInputID(), String.valueOf(qty));
		seleniumDriver.clickElement(LocatorType.ID, addToCartButtonID());
		seleniumDriver.getWebDriver().switchTo().defaultContent();
		totalPriceStr = seleniumDriver.getTextFromElement(LocatorType.XPATH, totaLabelXpath());

		return new LandingPage(seleniumDriver);
	}

	public LandingPage selectMenuAndSubMenu(int index){

		seleniumDriver.hoverOverMenuAndClickSubItemIfExist(LocatorType.XPATH, menuItemGenericXpath(index));
		return new LandingPage(seleniumDriver);
	}
    
    
}
