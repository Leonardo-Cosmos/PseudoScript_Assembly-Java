package org.pseudoscript.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.pseudoscript.selenium.config.OptionConfig;
import org.pseudoscript.selenium.config.SeleniumConfig;
import org.pseudoscript.selenium.config.WebDriverConfig;

public class DriverFactory {

	private static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";

	private static SeleniumConfig seleniumConfig;
	
	private static WebDriver chromeDriver;
	private static EventFiringWebDriver eventFiringWebDriver;

	static {
		seleniumConfig = SeleniumConfig.load();
	}
	
	public static WebDriver getChromeWebDriver() {
		if (chromeDriver == null) {
			chromeDriver = createChromeDriver();
		}
		return chromeDriver;
	}
	
	private static WebDriver createChromeDriver() {
		WebDriverConfig webDriverConfig = seleniumConfig.getWebDriver(
				SeleniumConfig.BROWSER_CHROME);
		OptionConfig optionConfig = seleniumConfig.getOption();
		if (webDriverConfig != null) {
			System.setProperty(CHROME_DRIVER_KEY, webDriverConfig.getPath());
		}
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setJavascriptEnabled(true);

		WebDriver webDriver = new ChromeDriver(capabilities);
		eventFiringWebDriver = new EventFiringWebDriver(webDriver);
		eventFiringWebDriver.manage().window().maximize();
		eventFiringWebDriver.manage().timeouts().implicitlyWait(
				optionConfig.getImplicitlyWait(), TimeUnit.SECONDS);
		eventFiringWebDriver.manage().timeouts().pageLoadTimeout(
				optionConfig.getPageLoadTimeout(), TimeUnit.SECONDS);
		
		return webDriver;
	}
}
