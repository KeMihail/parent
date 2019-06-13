package by.itacademy.keikom.taxi.web.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.web.util.ListModel;
import by.itacademy.keikom.taxi.web.util.SortModel;

@Component
public class SortLink extends SimpleTagSupport {

	private String column;

	@Override
	public void doTag() throws JspException, IOException {

		final JspContext jspContext = getJspContext();

		final ListModel<?> listModel = (ListModel<?>) getJspContext().findAttribute(ListModel.SESSION_ATTR_NAME);
		if (listModel == null) {
			throw new IllegalArgumentException(
					"context should have required attribute in session:" + ListModel.SESSION_ATTR_NAME);
		}

		final SortModel sort = listModel.getSort();
		if (sort == null) {
			throw new IllegalArgumentException("default sorting should be set in list model");
		}
		final String originalUrl = (String) getJspContext().getAttribute("pageurl");

		final String sortOrder = sort.isAscending() ? "desc" : "asc";

		final String href = String.format("%s?sort=%s:%s", originalUrl, column, sortOrder);

		final StringWriter tagBodyWriter = new StringWriter();
		getJspBody().invoke(tagBodyWriter); // copy tag body defined in jsp

		String sortIcon;
		if (column.equals(sort.getColumn())) {
			sortIcon = sort.isAscending() ? "<i class=\"material-icons\">keyboard_arrow_down</i>"
					: "<i class=\"material-icons\">keyboard_arrow_up</i>";
		} else {
			sortIcon = "";
		}

		jspContext.getOut().println(String.format("<a href=\"%s\">%s%s</a>", href, tagBodyWriter.toString(), sortIcon));
	}

	public void setColumn(final String column) {
		this.column = column;
	}
}
