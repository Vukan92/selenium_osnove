package d20_09_2022_tests;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import d20_09_2022_pages.BuyBoxPage;
import d20_09_2022_pages.HeaderPage;
import d20_09_2022_pages.LayerCartPage;
import d20_09_2022_pages.TopMenuPage;

public class AutomationPracticeTests {

	
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "http://automationpractice.com/";
	private BuyBoxPage buyBoxPage;
	private LayerCartPage layerCartPage;
	private TopMenuPage topMenuPage;
	private HeaderPage headerPage;
	private Actions actions;
	private SoftAssert softAssert;
	
	@BeforeClass 
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		buyBoxPage = new BuyBoxPage(driver, wait);
		layerCartPage = new LayerCartPage(driver, wait);
		topMenuPage = new TopMenuPage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		actions = new Actions(driver);
		softAssert = new SoftAssert();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}
	@Test (priority = 10)
	public void  AddingProductToTheCart() throws InterruptedException {
		driver.get(baseUrl + "/index.php?id_product=1&controller=product");
		buyBoxPage.scrollToBuyBoxElement();
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("3");
		buyBoxPage.getSizeOfItem().selectByVisibleText("L");
		buyBoxPage.getColorsofProduct("Blue").click();
		buyBoxPage.getAddToCartButton().click();
		layerCartPage.WaitForYourShoppingCartToBeVisible();
		
		Assert.assertEquals(layerCartPage.getProductQuantity().getText(), "3",
				"Quantity does not match");
		Assert.assertTrue(layerCartPage.getProductAttributes().getText().contains("L"),
				"Size does not match");
		Assert.assertTrue(layerCartPage.getProductAttributes().getText().contains("Blue"),
				"Color does not match");
		
		String productPriceText = buyBoxPage.getProductPrice().getText().replaceAll("[$]", "");
		String totalProductPriceText = layerCartPage.getProductTotalPrice().getText().replaceAll("[$]", "");
		double productPrice = Double.parseDouble(productPriceText);
		double totalProductPrice = Double.parseDouble(totalProductPriceText);
		Assert.assertEquals(productPrice * 3,
				totalProductPrice,
				"Price is not correct");
		
		layerCartPage.getContinueShoppingButton().click();
		layerCartPage.WaitForYourShoppingCartToBeInvisible();
		
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("1");
		buyBoxPage.getSizeOfItem().selectByVisibleText("S");
		buyBoxPage.getColorsofProduct("Orange").click();
		buyBoxPage.getAddToCartButton().click();
		layerCartPage.WaitForYourShoppingCartToBeVisible();
		
		layerCartPage.getProceedToCheckoutButton().click();
		Assert.assertEquals(driver.getTitle(), "Order - My Store",
				"Title does not match");
	}
	@Test (priority = 20)
	public void topMenuMouseOver(){
		
		actions.moveToElement(topMenuPage.getWomanLinkFromTopMenu()).perform();
		 softAssert.assertTrue(topMenuPage.getWomenSubmenu().isDisplayed(),
				 "Women submenu is not displayed");

		 actions.moveToElement(topMenuPage.getDressesLinkFromTopMenu()).perform();
		 softAssert.assertTrue(topMenuPage.getDressesSubmenu().isDisplayed(),
				 "Dresses submenu is not displayed");
		 
		 actions.moveToElement(topMenuPage.getTShirtsLinkFromTopMenu()).perform();
		 softAssert.assertFalse(
					topMenuPage.getDressesSubmenu().isDisplayed()
					&& topMenuPage.getWomenSubmenu().isDisplayed(),
					"At least one of the submenus is displayed");
	}
	@Test (priority = 30)
	public void phoneNumberVisibilityCheckOnResize() {
		driver.manage().window().maximize();
		softAssert.assertTrue(headerPage.getShopPhoneElement().isDisplayed(),
				"Phone number is NOT displayed");
		
		driver.manage().window().setSize(new Dimension(767, 700));
		softAssert.assertFalse(headerPage.getShopPhoneElement().isDisplayed(),
				"Phone number IS displayed(rez 767x700)");
		
		driver.manage().window().setSize(new Dimension(768, 700));
		softAssert.assertTrue(headerPage.getShopPhoneElement().isDisplayed(),
				"Phone number is NOT displayed(rez 768x700)");
		
		driver.manage().window().maximize();
	}
	@Test (priority = 40)
	public void headerLinksCheck() {
		
		headerPage.getContactUsLink().click();
		softAssert.assertEquals(driver.getTitle(), "Contact us - My Store",
				"Title does not match");
		
		headerPage.getSignInLink().click();
		softAssert.assertEquals(driver.getTitle(), "Login - My Store",
				"Title does not match");

	}

	@AfterMethod
	public void afterMethod() {
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
