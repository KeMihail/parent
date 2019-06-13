package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CarOrder extends AbstractModel implements Serializable {

	@Column
	private String departureAddress;

	@Column
	private String arrivalAddress;

	@Column
	private Timestamp orderBegin;

	@Column
	private Timestamp orderEnd;

	@Column
	private Double distanceOrder;

	@Column
	private Double distancePaid;

	@Column
	private Integer inactivityMinutes;

	@Column
	private Double total;

	@Column
	private Boolean deleted;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Rate.class)
	private Rate rate;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	private User client;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Driver.class)
	private Driver driver;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
	private OrderAssessment orderAssessment;

	public CarOrder() {
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public OrderAssessment getOrderAssessment() {
		return orderAssessment;
	}

	public void setOrderAssessment(OrderAssessment orderAssessment) {
		this.orderAssessment = orderAssessment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public String getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}

	public String getArrivalAddress() {
		return arrivalAddress;
	}

	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}

	public Timestamp getOrderBegin() {
		return orderBegin;
	}

	public void setOrderBegin(Timestamp orderBegin) {
		this.orderBegin = orderBegin;
	}

	public Timestamp getOrderEnd() {
		return orderEnd;
	}

	public void setOrderEnd(Timestamp orderEnd) {
		this.orderEnd = orderEnd;
	}

	public Double getDistanceOrder() {
		return distanceOrder;
	}

	public void setDistanceOrder(Double distanceOrder) {
		this.distanceOrder = distanceOrder;
	}

	public Double getDistancePaid() {
		return distancePaid;
	}

	public void setDistancePaid(Double distancePaid) {
		this.distancePaid = distancePaid;
	}

	public Integer getInactivityMinutes() {
		return inactivityMinutes;
	}

	public void setInactivityMinutes(Integer inactivityMinutes) {
		this.inactivityMinutes = inactivityMinutes;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
