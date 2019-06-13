package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.ServiceItem;
import by.itacademy.keikom.taxi.web.dto.ServiceItemDTO;

@Component
public class ServiceItemToDTOConverter implements Function<ServiceItem, ServiceItemDTO> {

	@Override
	public ServiceItemDTO apply(ServiceItem dbModel) {

		ServiceItemDTO dto = new ServiceItemDTO();

		dto.setId(dbModel.getId());
		dto.setItem(dbModel.getItem().toString());
		dto.setSumma(dbModel.getSumma());
		dto.setComment(dbModel.getComment());

		dto.setDriverId(dbModel.getDriver().getId());
		dto.setDriverName(dbModel.getDriver().getName());

		return dto;
	}
}
