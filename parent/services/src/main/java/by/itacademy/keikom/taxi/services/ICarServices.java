package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.filter.CarFilter;

public interface ICarServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	Car save(Car car);

	List<Car> getAll();

	Car get(Integer id);

	Long getCount(CarFilter filter);

	List<Car> getAll(CarFilter filter);
}
