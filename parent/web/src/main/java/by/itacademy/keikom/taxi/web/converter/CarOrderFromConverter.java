package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.web.dto.CarOrderDTO;

@Component
public class CarOrderFromConverter implements Function<CarOrderDTO, CarOrder> {

	@Override
	public CarOrder apply(CarOrderDTO dto) {

		CarOrder dbModel = new CarOrder();

		dbModel.setId(dto.getId());
		dbModel.setArrivalAddress(dto.getArrivalAddress());
		dbModel.setDepartureAddress(dto.getDepartureAddress());
		dbModel.setDistanceOrder(dto.getDistanceOrder());
		dbModel.setDistancePaid(dto.getDistancePaid());
		dbModel.setInactivityMinutes(dto.getInactivityMinutes());
		dbModel.setOrderBegin(dto.getOrderBegin());
		dbModel.setTotal((dto.getTotal()));
		dbModel.setDeleted(dto.getDeleted());

		Rate rate = new Rate();
		rate.setId(dto.getRateId());
		dbModel.setRate(rate);

		User client = new User();
		client.setId(dto.getClientId());
		dbModel.setClient(client);

		Driver driver = new Driver();
		driver.setId(dto.getDriverId());
		dbModel.setDriver(driver);

		return dbModel;
	}
}
