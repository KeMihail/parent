package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.web.dto.BrandDTO;

@Component
public class BrandToDTOConverter implements Function<Brand, BrandDTO> {

	@Override
	public BrandDTO apply(Brand dbModel) {

		BrandDTO dto = new BrandDTO();
		dto.setId(dbModel.getId());
		dto.setName(dbModel.getName());

		return dto;
	}

}
