package org.pseudoscript.selenium.config;

import javax.xml.bind.annotation.XmlElement;

public class OptionConfig {
	private int implicitlyWait;
	private int pageLoadTimeout;
	
	@XmlElement(name = "implicitlyWait")
	public int getImplicitlyWait() {
		return implicitlyWait;
	}
	public void setImplicitlyWait(int implicitlyWait) {
		this.implicitlyWait = implicitlyWait;
	}
	
	@XmlElement(name = "pageLoadTimeout")
	public int getPageLoadTimeout() {
		return pageLoadTimeout;
	}
	public void setPageLoadTimeout(int pageWaitTimeout) {
		this.pageLoadTimeout = pageWaitTimeout;
	}
}
