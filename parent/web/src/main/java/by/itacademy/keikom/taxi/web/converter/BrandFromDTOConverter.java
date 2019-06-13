package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.web.dto.BrandDTO;

@Component
public class BrandFromDTOConverter implements Function<BrandDTO, Brand> {

	@Override
	public Brand apply(BrandDTO dto) {

		Brand dbModel = new Brand();
		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());

		return dbModel;
	}

}
