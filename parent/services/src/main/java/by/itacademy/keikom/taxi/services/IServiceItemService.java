package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.ServiceItem;
import by.itacademy.keikom.taxi.dao.filter.ServiceItemFilter;

public interface IServiceItemService {

	@Transactional
	void remove(Integer id);

	@Transactional
	ServiceItem save(ServiceItem serviceItem);

	List<ServiceItem> getAll();

	ServiceItem get(Integer id);

	Long getCount(ServiceItemFilter filter);

	List<ServiceItem> getAll(ServiceItemFilter filter);

	List<ServiceItem> expensesToDriver(Integer id);

	Double allExpenses(List<ServiceItem> list);
}
