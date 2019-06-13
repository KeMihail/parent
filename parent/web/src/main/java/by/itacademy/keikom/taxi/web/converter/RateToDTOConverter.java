package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.web.dto.RateDTO;

@Component
public class RateToDTOConverter implements Function<Rate, RateDTO> {

	@Override
	public RateDTO apply(Rate dbModel) {

		RateDTO dto = new RateDTO();
		dto.setId(dbModel.getId());
		dto.setName(dbModel.getName());
		dto.setPriceKilometr(dbModel.getPriceKilometr());
		dto.setPriceLanding(dbModel.getPriceLanding());
		dto.setPriceMinuteWait(dbModel.getPriceMinuteWait());
		return dto;
	}
}
