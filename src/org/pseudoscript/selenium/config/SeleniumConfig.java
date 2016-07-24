package org.pseudoscript.selenium.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "selenium")
public class SeleniumConfig {

	public static final String BROWSER_CHROME = "Chrome";
	public static final String BROWSER_FIREFOX = "FireFox";
	public static final String BROWSER_IE = "IE";
	
	private static final String XML_FILE_PATH = "SeleniumConfig.xml";
	
	private static final Marshaller MARSHALLER;
	private static final Unmarshaller UNMARSHALLER;
	
	static {
		JAXBContext context;
		Marshaller marshaller = null;
		Unmarshaller unmarshaller = null;
		try {
			context = JAXBContext.newInstance(SeleniumConfig.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		MARSHALLER = marshaller;
		UNMARSHALLER = unmarshaller;
	}
	
	public static void save(SeleniumConfig config) {
		OutputStream output = null;
		try {
			output = new FileOutputStream(XML_FILE_PATH);
			MARSHALLER.marshal(config, output);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (JAXBException ex) {
			ex.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static SeleniumConfig load() {
		SeleniumConfig config = null;
		InputStream input = null;
		try {
			input = new FileInputStream(XML_FILE_PATH);
			config = (SeleniumConfig) UNMARSHALLER.unmarshal(input);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (JAXBException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return config;
	}
	
	private List<WebDriverConfig> webDrivers;	
	private OptionConfig option;
	
	@XmlElementWrapper(name = "webDrivers")
	@XmlElement(name = "webDriver")
	public List<WebDriverConfig> getWebDrivers() {
		return webDrivers;
	}
	public void setWebDrivers(List<WebDriverConfig> webDrivers) {
		this.webDrivers = webDrivers;
	}
	
	public WebDriverConfig getWebDriver(String browser) {
		if (browser == null) {
			throw new IllegalArgumentException();
		}
		
		for (WebDriverConfig driver : webDrivers) {
			if (browser.equalsIgnoreCase(driver.getBrowser())) {
				return driver;
			}
		}
		return null;
	}
	
	@XmlElement(name = "option")
	public OptionConfig getOption() {
		return option;
	}
	public void setOption(OptionConfig option) {
		this.option = option;
	}
	
}
