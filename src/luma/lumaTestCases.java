package luma;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class lumaTestCases {

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void setUp() {
		driver.get("https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test()
	public void addingTheFirstItem() throws InterruptedException {
		Random rand = new Random();
		driver.findElement(By.xpath("//img[@alt='Proteus Fitness Jackshirt']")).click();
//		List<WebElement> sizes = driver.findElements(By.className("text"));
//		List<WebElement> colors = driver.findElements(By.className("color"));

		// sizes
		WebElement sizeBox = driver
				.findElement(By.xpath("//div[@class='swatch-attribute size']//div[@role='listbox']"));
		List<WebElement> sizes = sizeBox.findElements(By.tagName("div"));
		int randSizes = rand.nextInt(sizes.size());
		WebElement randomSize = sizes.get(randSizes);
		randomSize.click();
		
		
		String expectedSize = driver.findElement(
				By.cssSelector("div[class='swatch-attribute size'] span[class='swatch-attribute-selected-option']"))
				.getText();
		String actualSize = randomSize.getText();
		Assert.assertEquals(actualSize, expectedSize);
		Thread.sleep(1000);

		// colors
		WebElement colorBox = driver
				.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
		List<WebElement> colors = colorBox.findElements(By.tagName("div"));
		int randColors = rand.nextInt(colors.size());
		WebElement randomColor = colors.get(randColors);
		randomColor.click();

		String expectedColor = colors.get(randColors).getAttribute("aria-label");
		WebElement selectedColor=driver.findElement(By.cssSelector("div[class='swatch-attribute color'] span[class='swatch-attribute-selected-option']"));
		String actualColor = selectedColor.getText();
		Assert.assertEquals(actualColor, expectedColor);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		WebElement msg=driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		String successMsg =msg.getText();
		Assert.assertEquals(successMsg.contains("added"), true);

	}

	@AfterTest
	public void after() {
	}

}
