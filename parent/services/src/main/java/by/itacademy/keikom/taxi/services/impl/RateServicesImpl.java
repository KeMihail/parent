package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IRateDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.filter.RateFilter;
import by.itacademy.keikom.taxi.services.IRateServices;

@Service
public class RateServicesImpl implements IRateServices {

	@Autowired
	private IRateDao dao;;

	@Override
	public void remove(final Integer id) {
		dao.remove(id);

	}

	@Override
	public Rate save(final Rate rate) {
		if (rate.getId() == null) {
			dao.insert(rate);
		} else {
			dao.update(rate);
		}
		return rate;
	}

	@Override
	public List<Rate> getAll() {
		return dao.getAll();
	}

	@Override
	public Rate get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(RateFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Rate> getAll(RateFilter filter) {
		return dao.find(filter);
	}
}
