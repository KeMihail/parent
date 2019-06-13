package by.itacademy.keikom.taxi.web.dto;

import java.sql.Timestamp;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RateDTO {

	private Integer id;
	@NotNull
	@Size(min = 1, max = 40)
	private String name;
	@NotNull
	@Min(1)
	@Max(10)
	private Double priceLanding;
	@NotNull
	@Min(1)
	@Max(10)
	private Double priceKilometr;
	@NotNull
	@Min(1)
	@Max(10)
	private Double priceMinuteWait;

	public RateDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
