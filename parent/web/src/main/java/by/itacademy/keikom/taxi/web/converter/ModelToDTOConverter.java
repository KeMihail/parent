package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.web.dto.ModelDTO;

@Component
public class ModelToDTOConverter implements Function<Model, ModelDTO> {

	@Override
	public ModelDTO apply(Model dbModel) {

		ModelDTO dto = new ModelDTO();

		dto.setId(dbModel.getId());
		dto.setName(dbModel.getName());
		dto.setCarKit(dbModel.getCarKit().toString());
		dto.setEngineType(dbModel.getEngineType().toString());
		dto.setBodyType(dbModel.getBodyType().toString());

		dto.setBrandId(dbModel.getBrand().getId());
		dto.setBrandName(dbModel.getBrand().getName());

		return dto;
	}

}
