package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class LegalEntity extends AbstractModel implements Serializable {

	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String phoneNumber;
	@Column
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "legalEntity")
	private List<Car> cars;

	public LegalEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
