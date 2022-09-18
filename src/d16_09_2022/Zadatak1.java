package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
//		Podesava:
//			implicitno cekanje za trazenje elemenata od 10s
//			implicitno cekanje za ucitavanje stranice od 10s
//			eksplicitno cekanje podeseno na 10s
//			Podaci:
//			Potrebno je u projektu ukljuciti 4 slike.
//			Imenovanje slika neka bude po pravilu pozicija_ime_prezime_polaznika.ekstenzija
//			Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg …
//			Koraci:
//			Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//			Maksimizuje prozor
//			Selektuje Image - Front klikom na tu karticu u dnu ekrana
//			Klik na add photo iz levog navigacionog menia
//			Upload slike. Upload preko File objekta koristeci getAbsolutePath
//			Sacekati da broj prikazanih slika u donjem uglu navigacije bude za 1 veca
//			Kliknuti na poslednje dodatu sliku kako bi bila izabrana za postavljanje
//			Ceka da dijalog bude vidljiv
//			Klik na Use One Side Only dugme
//			Ceka da se pojavi dijalog sa slikom
//			Klik na Done
//			Ponoviti proces za Left, Right i Back
//			Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//			Kliknuti na Add To Cart dugme
//			Verifikovati postojanje greske Oops! It looks like you`ve not added an text to this field, please add one before continuing.
//			Xpath: //*[@action='error']
//			Zatvorite pretrazivac
		
		System.setProperty("webdriver.chrome.driver" , "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		ArrayList<File> slike = new ArrayList<File>();
		Random r = new Random();
		
		slike.add(new File("img/front_vukan_djokic.jpg"));
		slike.add(new File("img/left_vukan_djokic.jpg"));
		slike.add(new File("img/back_vukan_djokic.jpg"));
		slike.add(new File("img/right_vukan_djokic.jpg"));
		
		for (int i = 0; i < slike.size(); i++) {
			driver.findElement(By.xpath("//*[@alt='image " + (i + 1) + "']")).click();
			driver.findElement(By.xpath("//*[(@alt ='+ Add photo')]")).click();
			driver.findElement(By.id("imageUpload")).sendKeys(slike.get(i).getAbsolutePath());
			wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@loading = 'lazy']"), i + 1));
			driver.findElement(By.xpath("//*[@loading = 'lazy']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Use One Side Only']")));
			driver.findElement(By.xpath("//button[text()='Use One Side Only']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'reactEasyCrop_Container')]")));
			driver.findElement(By.xpath("//*[text()= 'Done']")).click();
		}
		
		List<WebElement> konfete = driver.findElements(By.xpath("//*[contains(@class, 'kFlYLE')]/div"));
		konfete.get(r.nextInt(konfete.size())).click();
		
		driver.findElement(By.xpath("//button[text() = 'Add to cart ']")).click();
		
		boolean exists = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@action = 'error']")));

		} catch (Exception e) {
			exists = false;
		}

		if (exists) {
			System.out.println("Error");
		} else {
			System.out.println("No Error");
		}

		Thread.sleep(3000);
		driver.quit();
	}

}
