package by.itacademy.keikom.taxi.services.impl;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICarOrderDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.filter.CarOrderFilter;
import by.itacademy.keikom.taxi.services.ICarOrderServices;
import by.itacademy.keikom.taxi.services.IRateServices;
import by.itacademy.keikom.taxi.services.IUserServices;

@Service
public class CarOrderServicesImpl implements ICarOrderServices {

	@Autowired
	private ICarOrderDao dao;
	@Autowired
	private IUserServices userService;
	@Autowired
	private IRateServices rateService;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public CarOrder save(CarOrder order) {

		order.setDeleted(false);

		if (order.getId() == null) {
			order.setOrderBegin((Timestamp) new Date());

			User user = userService.get(order.getClient().getId());
			userService.save(user);
			order.setDriver(null);
			order.setRate(null);
			dao.insert(order);
		} else {

			Rate rate = rateService.get(order.getRate().getId());
			Double landing = rate.getPriceLanding();
			Double priceKilometr = rate.getPriceKilometr() * order.getDistanceOrder();
			Double wait = rate.getPriceMinuteWait() * order.getInactivityMinutes();

			DecimalFormat newFormat = new DecimalFormat("#.##");
			order.setTotal(Double.valueOf(newFormat.format(landing + priceKilometr + wait)));
			dao.update(order);
		}
		return order;
	}

	@Override
	public List<CarOrder> getAll() {

		return dao.getAll();
	}

	@Override
	public CarOrder get(Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(CarOrderFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<CarOrder> getAll(CarOrderFilter filter) {
		return dao.find(filter);
	}

	@Override
	public Integer getLatestOrderId() {
		return dao.getLatestOrderId();
	}

	@Override
	public List<CarOrder> ordersToClient(Integer id, String role) {
		return dao.ordersToClient(id, role);
	}

	@Override
	public Double revenue(List<CarOrder> list) {
		return dao.revenue(list);
	}
}
