package by.itacademy.keikom.taxi.dao.dbmodel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car2CarOption {

	@Id
	@AttributeOverrides({ @AttributeOverride(name = "carId", column = @Column(name = "carId")),
			@AttributeOverride(name = "carOptionId", column = @Column(name = "carOptionId")) })

	private Integer carId;
	private Integer carOptionId;

	public Car2CarOption() {

	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarOptionId() {
		return carOptionId;
	}

	public void setCarOptionId(Integer carOptionId) {
		this.carOptionId = carOptionId;
	}
}
