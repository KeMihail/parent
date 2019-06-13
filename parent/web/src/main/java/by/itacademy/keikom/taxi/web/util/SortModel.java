package by.itacademy.keikom.taxi.web.util;

public class SortModel {

	private String column;

	private boolean ascending;

	public SortModel(final String column) {
		this(column, false);
	}

	public SortModel(final String column, final boolean ascending) {
		super();
		this.column = column;
		this.ascending = ascending;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(final String column) {
		this.column = column;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(final boolean ascending) {
		this.ascending = ascending;
	}

}
