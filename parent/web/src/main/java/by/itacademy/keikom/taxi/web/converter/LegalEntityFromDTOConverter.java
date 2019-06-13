package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Controller;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.web.dto.LegalEntityDTO;

@Controller
public class LegalEntityFromDTOConverter implements Function<LegalEntityDTO, LegalEntity> {

	@Override
	public LegalEntity apply(LegalEntityDTO dto) {

		LegalEntity dbModel = new LegalEntity();

		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());
		dbModel.setAddress(dto.getAddress());
		dbModel.setEmail(dto.getEmail());
		dbModel.setPhoneNumber(dto.getPhoneNumber());

		return dbModel;
	}

}
