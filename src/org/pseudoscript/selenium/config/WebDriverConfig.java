package org.pseudoscript.selenium.config;

import javax.xml.bind.annotation.XmlAttribute;

public class WebDriverConfig {
	private String browser;
	private String path;
	
	@XmlAttribute(name = "browser")
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	@XmlAttribute(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
