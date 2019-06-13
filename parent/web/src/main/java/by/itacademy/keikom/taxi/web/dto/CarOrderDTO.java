package by.itacademy.keikom.taxi.web.dto;

import java.sql.Timestamp;
import java.util.Date;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;

public class CarOrderDTO {

	private Integer id;
	private String departureAddress;
	private String arrivalAddress;
	private Timestamp orderBegin;
	private Date orderEnd;
	private Double distanceOrder;
	private Double distancePaid;
	private Integer inactivityMinutes;
	private Double total;
	private Boolean deleted;

	private Integer rateId;
	private String rateName;

	private Integer clientId;
	private String clientName;

	private Integer driverId;
	private String driverName;

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getOrderEnd() {
		return orderEnd;
	}

	public void setOrderEnd(Date orderEnd) {
		this.orderEnd = orderEnd;
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

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getRateId() {
		return rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
}
