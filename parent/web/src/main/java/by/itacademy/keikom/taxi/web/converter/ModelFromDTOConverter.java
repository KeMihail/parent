package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.enums.BodyType;
import by.itacademy.keikom.taxi.dao.enums.CarKit;
import by.itacademy.keikom.taxi.dao.enums.EngineType;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.web.dto.ModelDTO;

@Component
public class ModelFromDTOConverter implements Function<ModelDTO, Model> {

	@Override
	public Model apply(ModelDTO dto) {

		Model dbModel = new Model();

		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());
		dbModel.setCarKit(CarKit.valueOf(dto.getCarKit()));
		dbModel.setEngineType(EngineType.valueOf(dto.getEngineType()));
		dbModel.setBodyType(BodyType.valueOf(dto.getBodyType()));

		Brand brand = new Brand();
		brand.setId(dto.getBrandId());
		dbModel.setBrand(brand);

		return dbModel;
	}

}
