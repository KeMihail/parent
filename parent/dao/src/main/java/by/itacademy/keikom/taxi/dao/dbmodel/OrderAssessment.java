package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class OrderAssessment implements Serializable {

	@Id
	private Integer orderId;;

	@Column
	private Integer assessment;

	@Column
	private String comment;

	@Column
	private Timestamp created;

	@Column
	private Timestamp modified;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@PrimaryKeyJoinColumn
	private CarOrder order;

	public OrderAssessment() {

	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public CarOrder getOrder() {
		return order;
	}

	public void setOrder(CarOrder order) {
		this.order = order;
	}

	public Integer getAssessment() {
		return assessment;
	}

	public void setAssessment(Integer assessment) {
		this.assessment = assessment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
