package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.enums.Role;
import by.itacademy.keikom.taxi.web.dto.DriverDTO;

@Component
public class DriverFromDTOConverter implements Function<DriverDTO, Driver> {

	@Override
	public Driver apply(DriverDTO dto) {

		Driver dbModel = new Driver();

		dbModel.setId(dto.getId());
		dbModel.setAddress(dto.getAddress());
		dbModel.setDeleted(dto.getDeleted());
		dbModel.setEmail(dto.getEmail());
		dbModel.setLastName(dto.getLastName());
		dbModel.setName(dto.getName());
		dbModel.setPassword(dto.getPassword());
		dbModel.setPhoneNumber(dto.getPhoneNumber());
		dbModel.setBirthday(dto.getBirthday());

		Car car = new Car();
		car.setId(dto.getCarId());
		dbModel.setCar(car);

		return dbModel;
	}
}
