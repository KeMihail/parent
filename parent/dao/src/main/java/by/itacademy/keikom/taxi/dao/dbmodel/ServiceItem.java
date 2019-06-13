package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.keikom.taxi.dao.enums.EServiceItem;

@Entity
public class ServiceItem extends AbstractModel implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Driver.class)
	private Driver driver;

	@Column
	@Enumerated(value = EnumType.STRING)
	private EServiceItem item;

	@Column
	private Double summa;

	@Column
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Double getSumma() {
		return summa;
	}

	public void setSumma(Double summa) {
		this.summa = summa;
	}

	public EServiceItem getItem() {
		return item;
	}

	public void setItem(EServiceItem item) {
		this.item = item;
	}
}
