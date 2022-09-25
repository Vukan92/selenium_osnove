package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyBoxPage {
//	BuyBoxPage koja pribavlja:
//		input za kolicinu
//		select za velicinu
//		add to cart dugme
//		add to wishlist dugme
//		metodu koja vraca element boje. 
//		Metoda kao parametar prima naziv boje 
//		(npr: “Orange”, “Blue”) a vraca link koji ima atribut title
//		prema trazenoj vrednosti.
//		metodu koja skrola do ovog dela stranice
	
	private WebDriver driver;

	
	public BuyBoxPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
	}
	
	public WebElement getQuantityInput() {
		return driver.findElement(By.id("quantity_wanted"));
	}
	public Select getSizeOfItem() {
		return new Select(driver.findElement(By.id("group_1")));
	}
	
	public WebElement getAddToCartButton() {
		return driver.findElement(By.xpath("//*[(@id = 'add_to_cart')]/button"));
	}
	
	public WebElement getAddToWishlistButton() {
		return driver.findElement(By.id("wishlist_button"));
	}
	
	public WebElement getColorsofProduct(String color) {
		return driver.findElement(By.xpath("//*[@id ='color_to_pick_list']/li/a[contains(@title, '" + color + "')]"));
	}
	
	public WebElement getProductPrice() {
		return driver.findElement(By.id("our_price_display"));
	}
	
	public void scrollToBuyBoxElement() {
		Actions actions = new Actions(driver);
		actions.scrollToElement(driver.findElement(By.id("wishlist_button")));
		actions.perform();
	}
}
