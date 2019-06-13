package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import by.itacademy.keikom.taxi.dao.enums.Role;

@Entity
public class Driver extends AbstractUser implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Car.class)
	private Car car;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
	private List<CarOrder> orderDriver;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "driver")
	private Set<ServiceItem> serviceItem;

	public Driver() {

	}

	public Car getCar() {
		return car;
	}

	public Set<ServiceItem> getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(Set<ServiceItem> serviceItem) {
		this.serviceItem = serviceItem;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<CarOrder> getOrderDriver() {
		return orderDriver;
	}

	public void setOrderDriver(List<CarOrder> orderDriver) {
		this.orderDriver = orderDriver;
	}

}
