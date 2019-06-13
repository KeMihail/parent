package by.itacademy.keikom.taxi.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class i18nLink extends SimpleTagSupport {

	private String key;

	@Override
	public void doTag() throws JspException, IOException {

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
