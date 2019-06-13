package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.filter.RateFilter;

public interface IRateDao extends IHibernateDao<Rate, Integer> {

	Long count(RateFilter filter);

	List<Rate> find(RateFilter filter);

}
