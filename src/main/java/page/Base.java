package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	public static WebDriver driver;

	public void initDriver() {
		System.setProperty("webdriver.chrome.driver","./driver executables/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://2048game.com/");
	}
}
