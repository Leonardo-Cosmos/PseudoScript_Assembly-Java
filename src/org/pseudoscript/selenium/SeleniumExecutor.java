package org.pseudoscript.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pseudoscript.assembly.annotation.Executor;
import org.pseudoscript.assembly.annotation.Operation;
import org.pseudoscript.assembly.annotation.Parameter;

@Executor(name = "Selenium")
public class SeleniumExecutor {

	private WebDriver driver;

	public SeleniumExecutor() {
		
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Operation(name = "OpenURL", parameters = { @Parameter(type = "String", name = "Url") })
	public void openURL(String url) {
		driver.get(url);
	}

	@Operation(name = "InputText", parameters = { @Parameter(type = "String", name = "XPath"),
			@Parameter(type = "String", name = "Text") })
	public void inputText(String xPath, String text) {
		WebElement inputTextbox = driver.findElement(By.xpath(xPath));
		inputTextbox.sendKeys(text);
	}

	@Operation(name = "Click", parameters = { @Parameter(type = "String", name = "XPath") })
	public void click(String xPath) {
		WebElement element = driver.findElement(By.xpath(xPath));
		element.click();
	}

}
