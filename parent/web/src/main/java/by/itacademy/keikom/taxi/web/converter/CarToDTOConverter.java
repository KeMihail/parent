package by.itacademy.keikom.taxi.web.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.web.dto.CarDTO;

@Component
public class CarToDTOConverter implements Function<Car, CarDTO> {

	@Override
	public CarDTO apply(Car dbModel) {

		CarDTO dto = new CarDTO();

		dto.setId(dbModel.getId());
		dto.setReleaseYear(dbModel.getReleaseYear());

		dto.setModelId(dbModel.getModel().getId());
		dto.setModelName(dbModel.getModel().getName());

		dto.setLegalEntityId(dbModel.getLegalEntity().getId());
		dto.setLegalEntityName(dbModel.getLegalEntity().getName());

		dto.setStatus(dbModel.getStatus().toString());

		final Set<CarOption> allCarOption = dbModel.getCarOption();
		final Set<Integer> carOptionId = new HashSet<Integer>();

		if (allCarOption != null) {
			for (CarOption carOption : allCarOption) {
				carOptionId.add(carOption.getId());
			}
		}

		dto.setCarOptionId(carOptionId);

		return dto;
	}

}
