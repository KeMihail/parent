package by.itacademy.keikom.taxi.dao.dbmodel;

import by.itacademy.keikom.taxi.dao.enums.BodyType;
import by.itacademy.keikom.taxi.dao.enums.CarKit;
import by.itacademy.keikom.taxi.dao.enums.EngineType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Model extends AbstractModel implements Serializable {

	@Column
	private String name;

	@Enumerated(value = EnumType.STRING)
	@Column
	private CarKit carKit;

	@Enumerated(value = EnumType.STRING)
	@Column
	private EngineType engineType;

	@Enumerated(value = EnumType.STRING)
	@Column
	private BodyType BodyType;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Brand.class)
	private Brand brand;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "model")
	private List<Car> cars;

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Model() {
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarKit getCarKit() {
		return carKit;
	}

	public void setCarKit(CarKit carKit) {
		this.carKit = carKit;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}

	public BodyType getBodyType() {
		return BodyType;
	}

	public void setBodyType(BodyType eBodyType) {
		BodyType = eBodyType;
	}
}
