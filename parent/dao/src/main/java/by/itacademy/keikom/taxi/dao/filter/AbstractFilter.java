package by.itacademy.keikom.taxi.dao.filter;

import java.io.Serializable;

import javax.persistence.metamodel.SingularAttribute;

public class AbstractFilter implements Serializable {

	private SingularAttribute sortProperty;
	private boolean sortOrder;
	private Integer offset;
	private Integer limit;

	public SingularAttribute getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(final SingularAttribute sortProperty) {
		this.sortProperty = sortProperty;
	}

	public boolean isSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(final boolean sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(final Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(final Integer limit) {
		this.limit = limit;
	}

}