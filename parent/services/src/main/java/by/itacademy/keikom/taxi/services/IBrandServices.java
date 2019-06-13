package by.itacademy.keikom.taxi.services;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.filter.BrandFilter;

public interface IBrandServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	Brand save(Brand brand);

	List<Brand> getAll();

	Brand get(Integer id);

	Long getCount(BrandFilter filter);

	List<Brand> getAll(BrandFilter filter);
}
