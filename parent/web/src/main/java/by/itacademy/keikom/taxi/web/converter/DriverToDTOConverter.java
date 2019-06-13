package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.web.dto.DriverDTO;

@Component
public class DriverToDTOConverter implements Function<Driver, DriverDTO> {

	@Override
	public DriverDTO apply(Driver dbModel) {

		DriverDTO dto = new DriverDTO();

		dto.setId(dbModel.getId());
		dto.setAddress(dbModel.getAddress());
		dto.setCarBrand(dbModel.getCar().getModel().getBrand().getName());
		dto.setCarId(dbModel.getCar().getId());
		dto.setDeleted(dbModel.getDeleted());
		dto.setEmail(dbModel.getEmail());
		dto.setLastName(dbModel.getLastName());
		dto.setName(dbModel.getName());
		dto.setPassword(dbModel.getPassword());
		dto.setPhoneNumber(dbModel.getPhoneNumber());
		dto.setBirthday(dbModel.getBirthday());

		return dto;
	}

}
