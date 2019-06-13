package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Controller;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.web.dto.LegalEntityDTO;

@Controller
public class LegalEntityToDTOConverter implements Function<LegalEntity, LegalEntityDTO> {

	@Override
	public LegalEntityDTO apply(LegalEntity dbModel) {

		LegalEntityDTO dto = new LegalEntityDTO();

		dto.setId(dbModel.getId());
		dto.setName(dbModel.getName());
		dto.setAddress(dbModel.getAddress());
		dto.setEmail(dbModel.getEmail());
		dto.setPhoneNumber(dbModel.getPhone_number());

		return dto;
	}

}
