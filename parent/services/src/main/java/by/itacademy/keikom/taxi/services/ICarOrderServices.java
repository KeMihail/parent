package by.itacademy.keikom.taxi.services;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.filter.CarOrderFilter;

public interface ICarOrderServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	CarOrder save(CarOrder order);

	List<CarOrder> getAll();

	CarOrder get(Integer id);

	Long getCount(CarOrderFilter filter);

	List<CarOrder> getAll(CarOrderFilter filter);

	Integer getLatestOrderId();

	List<CarOrder> ordersToClient(Integer id, String role);

	Double revenue(List<CarOrder> list);

}
