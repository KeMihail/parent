package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.ServiceItem;
import by.itacademy.keikom.taxi.dao.filter.ServiceItemFilter;

public interface IServiceItemDao extends IHibernateDao<ServiceItem, Integer> {

	Long count(ServiceItemFilter filter);

	List<ServiceItem> find(ServiceItemFilter filter);

	public List<ServiceItem> expensesToDriver(Integer id);

	Double allExpenses(List<ServiceItem> list);

}
