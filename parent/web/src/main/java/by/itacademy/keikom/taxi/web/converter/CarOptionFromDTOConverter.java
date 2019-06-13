package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.web.dto.CarOptionDTO;

@Component
public class CarOptionFromDTOConverter implements Function<CarOptionDTO, CarOption> {

	@Override
	public CarOption apply(CarOptionDTO dto) {

		CarOption dbModel = new CarOption();

		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());

		return dbModel;
	}

}
