package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.web.dto.CarOptionDTO;

@Component
public class CarOptionToDTOConverter implements Function<CarOption, CarOptionDTO> {

	@Override
	public CarOptionDTO apply(CarOption dbModel) {

		CarOptionDTO dto = new CarOptionDTO();

		dto.setId(dbModel.getId());
		dto.setName(dbModel.getName());

		return dto;
	}

}
