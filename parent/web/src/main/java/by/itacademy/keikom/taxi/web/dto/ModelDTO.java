package by.itacademy.keikom.taxi.web.dto;

public class ModelDTO {

	private Integer id;
	private String name;
	private String carKit;
	private String engineType;
	private String bodyType;

	private Integer brandId;
	private String brandName;

	public ModelDTO() {
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getCarKit() {
		return carKit;
	}

	public void setCarKit(String carKit) {
		this.carKit = carKit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
}
