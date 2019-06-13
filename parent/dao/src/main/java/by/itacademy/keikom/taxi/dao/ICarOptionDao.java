package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.filter.CarOptionFilter;

public interface ICarOptionDao extends IHibernateDao<CarOption, Integer> {

	Long count(CarOptionFilter filter);

	List<CarOption> find(CarOptionFilter filter);
}
