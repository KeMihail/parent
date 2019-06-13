package by.itacademy.keikom.taxi.web.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;

public class OrderAssessmentDTO {

	private Integer orderId;
	private Integer assessment;
	private String comment;

	public OrderAssessmentDTO() {

	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
