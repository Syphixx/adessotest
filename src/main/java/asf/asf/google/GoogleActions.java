package asf.asf.google;

import org.openqa.selenium.Keys;
import org.testng.Assert;

import asf.asf.general.Selenium;

public class GoogleActions extends Selenium {
	public static void googleSearch(String search) {
		driver.findElement(GoogleElements.inputBox).clear();
		driver.findElement(GoogleElements.inputBox).sendKeys(search);
		driver.findElement(GoogleElements.inputBox).sendKeys(Keys.ENTER);
	}
	
	public static void googleVerifySpanText(String expectedText) {
		String actualText = driver.findElement(GoogleElements.spanBox).getText();
		Assert.assertEquals(actualText, expectedText);
	}
}