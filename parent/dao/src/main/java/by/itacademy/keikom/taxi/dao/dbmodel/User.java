package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import by.itacademy.keikom.taxi.dao.enums.Role;

@Entity
public class User extends AbstractUser implements Serializable {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<CarOrder> ordersClient;

	public User() {
	}

	public List<CarOrder> getOrdersClient() {
		return ordersClient;
	}

	public void setOrdersClient(List<CarOrder> ordersClient) {
		this.ordersClient = ordersClient;
	}

}
