package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.filter.DriverFilter;

public interface IDriverDao extends IHibernateDao<Driver, Integer> {

	Long count(DriverFilter filter);

	List<Driver> find(DriverFilter filter);

	Driver getFullInfo(Integer id);

	Driver loadByLogin(String email);

	List<String> loadAllEmail();
}
