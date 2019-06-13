package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.filter.CarOrderFilter;

public interface ICarOrderDao extends IHibernateDao<CarOrder, Integer> {

	Long count(CarOrderFilter filter);

	List<CarOrder> find(CarOrderFilter filter);

	Integer getLatestOrderId();

	List<CarOrder> ordersToClient(Integer id, String role);

	Double revenue(List<CarOrder> list);
}
