package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuPage {
//	TopMenuPage koja pribavlja:
//		metodu koja vraca WOMEN link iz glavnom menija
//		metodu koja vraca DRESSES link iz glavnom menija
//		metodu koja vraca T-SHIRTS link iz glavnom menija
//		metodu koja vraca podmeni za WOMEN
//		metodu koja vraca podmeni za DRESSES
	
	private WebDriver driver;

	public TopMenuPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
	}
	
	public WebElement getWomanLinkFromTopMenu() {
		return driver.findElement(By.xpath("//*[(@id = 'block_top_menu')]/ul/li[1]/a"));
	}
	
	public WebElement getDressesLinkFromTopMenu() {
		return driver.findElement(By.xpath("//*[(@id = 'block_top_menu')]/ul/li[2]/a"));
	}
	
	public WebElement getTShirtsLinkFromTopMenu() {
		return driver.findElement(By.xpath("//*[(@id = 'block_top_menu')]/ul/li[3]/a"));
	}
	
	public WebElement getWomenSubmenu() {
		return driver.findElement(By.xpath("//*[(@id = 'block_top_menu')]/ul/li[1]/ul"));
	}
	
	public WebElement getDressesSubmenu() {
		return driver.findElement(By.xpath("//*[(@id = 'block_top_menu')]/ul/li[2]/ul"));
	}
}
