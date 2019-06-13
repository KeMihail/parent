package by.itacademy.keikom.taxi.services;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.filter.DriverFilter;

public interface IDriverServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	Driver save(Driver model);

	List<Driver> getAll();

	Driver get(Integer id);

	Long getCount(DriverFilter filter);

	List<Driver> getAll(DriverFilter filter);

	Driver getFullInfo(Integer id);

	Driver loadByLogin(String email);

	List<String> loadAllEmail();

	Boolean checkEmail(String email);
}
