package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Rate extends AbstractModel implements Serializable {

	@Column
	private String name;

	@Column
	private Double priceLanding;

	@Column
	private Double priceKilometr;

	@Column
	private Double priceMinuteWait;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rate")
	List<CarOrder> order;

	public List<CarOrder> getOrder() {
		return order;
	}

	public void setOrder(List<CarOrder> order) {
		this.order = order;
	}

	public Rate() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPriceLanding() {
		return priceLanding;
	}

	public void setPriceLanding(Double priceLanding) {
		this.priceLanding = priceLanding;
	}

	public Double getPriceKilometr() {
		return priceKilometr;
	}

	public void setPriceKilometr(Double priceKilometr) {
		this.priceKilometr = priceKilometr;
	}

	public Double getPriceMinuteWait() {
		return priceMinuteWait;
	}

	public void setPriceMinuteWait(Double priceMinuteWait) {
		this.priceMinuteWait = priceMinuteWait;
	}
}
