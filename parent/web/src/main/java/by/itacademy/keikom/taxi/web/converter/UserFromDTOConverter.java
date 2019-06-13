package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.enums.Role;
import by.itacademy.keikom.taxi.web.dto.UserDTO;

@Component
public class UserFromDTOConverter implements Function<UserDTO, User> {

	@Override
	public User apply(UserDTO dto) {

		User dbModel = new User();

		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());
		dbModel.setLastName(dto.getLastName());
		dbModel.setBirthday(dto.getBirthday());
		dbModel.setAddress(dto.getAddress());
		dbModel.setPhoneNumber(dto.getPhoneNumber());
		dbModel.setEmail(dto.getEmail());
		dbModel.setDeleted(dto.getDeleted());

		dbModel.setRole(Role.valueOf(dto.getRole()));
		dbModel.setPassword(dto.getPassword());

		return dbModel;
	}

}
