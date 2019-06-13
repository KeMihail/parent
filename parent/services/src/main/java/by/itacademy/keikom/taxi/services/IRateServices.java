package by.itacademy.keikom.taxi.services;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.filter.RateFilter;

public interface IRateServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	Rate save(Rate rate);

	List<Rate> getAll();

	Rate get(Integer id);

	Long getCount(RateFilter filter);

	List<Rate> getAll(RateFilter filter);
}
