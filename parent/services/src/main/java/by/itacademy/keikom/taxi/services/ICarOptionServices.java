package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.filter.CarOptionFilter;

public interface ICarOptionServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	CarOption save(CarOption carOption);

	List<CarOption> getAll();

	CarOption get(Integer id);

	Long getCount(CarOptionFilter filter);

	List<CarOption> getAll(CarOptionFilter filter);
}
