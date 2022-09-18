package d15_09_2022;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
//		Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//			Klikce na svaki iks da ugasi obavestenje i 
//			proverava da li se nakon klika element obrisao sa stranice i 
//			ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//			POMOC: Brisite elemente odozdo.
//			(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo
		
		
		System.setProperty("webdriver.chrome.driver" , "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://s.bootsnipp.com/iframe/Dq2X");
		List<WebElement> button = driver.findElements(By.className("close"));

		boolean exist = true;
		for (int i = button.size() - 1; i >= 0; i--) {
			button.get(i).click();
			Thread.sleep(2000);
			try {
				button.get(i).click();
			} catch (Exception e) {
				exist = false;
			}

			if (!exist) {
				System.out.println("Pop-up je iskljucen");
			} else {
				System.out.println("Pop-up nije iskljucen");
			}

		}
			
		Thread.sleep(3000);
		driver.quit();
	}

}
