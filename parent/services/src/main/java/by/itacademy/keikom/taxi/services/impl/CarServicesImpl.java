package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICarDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.filter.CarFilter;
import by.itacademy.keikom.taxi.services.ICarServices;

@Service
public class CarServicesImpl implements ICarServices {

	@Autowired
	private ICarDao dao;

	@Override
	public void remove(final Integer id) {
		dao.remove(id);

	}

	@Override
	public Car save(final Car car) {
		if (car.getId() == null) {
			dao.insert(car);
		} else {
			dao.update(car);
		}
		return car;
	}

	@Override
	public List<Car> getAll() {
		return dao.getAll();
	}

	@Override
	public Car get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(CarFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Car> getAll(CarFilter filter) {
		return dao.find(filter);
	}
}
