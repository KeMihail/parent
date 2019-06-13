package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.filter.BrandFilter;

public interface IBrandDao extends IHibernateDao<Brand, Integer> {

	Long count(BrandFilter filter);

	List<Brand> find(BrandFilter filter);
}
