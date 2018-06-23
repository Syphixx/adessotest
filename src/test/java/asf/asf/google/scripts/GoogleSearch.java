package asf.asf.google.scripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import asf.asf.general.Selenium;
import asf.asf.google.GoogleActions;

public class GoogleSearch extends Selenium {
	
	@Parameters({"search"})
	@Test(priority = 0)
	public void googleSearch(String search) {
		GoogleActions.googleSearch(search);
	}
	
	@Parameters({"verify"})
	@Test(priority = 1)
	public void googleVerifySpanText(String verify) {
		GoogleActions.googleVerifySpanText(verify);
	}
}