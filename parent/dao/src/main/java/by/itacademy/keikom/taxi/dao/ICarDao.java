package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.filter.CarFilter;

public interface ICarDao extends IHibernateDao<Car, Integer> {

	Long count(CarFilter filter);

	List<Car> find(CarFilter filter);
}
