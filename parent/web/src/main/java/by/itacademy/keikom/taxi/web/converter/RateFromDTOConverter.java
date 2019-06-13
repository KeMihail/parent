package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.web.dto.RateDTO;

@Component
public class RateFromDTOConverter implements Function<RateDTO, Rate> {

	@Override
	public Rate apply(RateDTO dto) {

		Rate dbModel = new Rate();
		dbModel.setId(dto.getId());
		dbModel.setName(dto.getName());
		dbModel.setPriceKilometr(dto.getPriceKilometr());
		dbModel.setPriceLanding(dto.getPriceLanding());
		dbModel.setPriceMinuteWait(dto.getPriceMinuteWait());

		return dbModel;
	}

}
