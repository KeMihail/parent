package by.itacademy.keikom.taxi.dao.dbmodel;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class CarOption extends AbstractModel implements Serializable {

	@Column
	private String name;

	public CarOption() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}