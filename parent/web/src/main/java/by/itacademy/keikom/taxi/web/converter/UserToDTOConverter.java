package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.web.dto.UserDTO;

@Component
public class UserToDTOConverter implements Function<User, UserDTO> {

	@Override
	public UserDTO apply(User dbModel) {

		UserDTO dto = new UserDTO();

		dto.setId(dbModel.getId());
		dto.setName(dbModel.getName());
		dto.setLastName(dbModel.getLastName());
		dto.setBirthday(dbModel.getBirthday());
		dto.setAddress(dbModel.getAddress());
		dto.setPhoneNumber(dbModel.getPhoneNumber());
		dto.setEmail(dbModel.getEmail());
		dto.setDeleted(dbModel.getDeleted());

		dto.setPassword(dbModel.getPassword());
		dto.setRole(dbModel.getRole().toString());

		return dto;
	}

}
