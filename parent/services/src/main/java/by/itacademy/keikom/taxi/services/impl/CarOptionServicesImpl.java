package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ICarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.filter.CarOptionFilter;
import by.itacademy.keikom.taxi.services.ICarOptionServices;

@Service
public class CarOptionServicesImpl implements ICarOptionServices {

	@Autowired
	private ICarOptionDao dao;

	@Override
	public void remove(final Integer id) {
		dao.remove(id);
	}

	@Override
	public CarOption save(final CarOption carOption) {
		if (carOption.getId() == null) {
			dao.insert(carOption);
		} else {
			dao.update(carOption);
		}
		return carOption;
	}

	@Override
	public List<CarOption> getAll() {
		return dao.getAll();
	}

	@Override
	public CarOption get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(CarOptionFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<CarOption> getAll(CarOptionFilter filter) {
		return dao.find(filter);
	}
}
