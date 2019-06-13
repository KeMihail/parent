package by.itacademy.keikom.taxi.web.converter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.enums.CarStatus;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.web.dto.CarDTO;

@Component
public class CarFromDTOConverter implements Function<CarDTO, Car> {

	@Override
	public Car apply(CarDTO dto) {

		Car dbModel = new Car();

		dbModel.setId(dto.getId());
		dbModel.setReleaseYear(dto.getReleaseYear());

		Model model = new Model();
		model.setId(dto.getModelId());
		dbModel.setModel(model);

		LegalEntity LegalEntity = new LegalEntity();
		LegalEntity.setId(dto.getLegalEntityId());
		dbModel.setLegalEntity(LegalEntity);

		dbModel.setStatus(CarStatus.valueOf(dto.getStatus()));

		final Set<Integer> carOptionId = new HashSet<Integer>();
		final Set<CarOption> carOptions = new HashSet<CarOption>();

		for (final Integer id : carOptionId) {
			final CarOption carOption = new CarOption();
			carOption.setId(id);
			carOptions.add(carOption);
		}

		dbModel.setCarOption(carOptions);
		return dbModel;
	}
}
