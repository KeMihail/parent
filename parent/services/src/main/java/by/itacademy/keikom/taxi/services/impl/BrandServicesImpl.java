package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IBrandDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.filter.BrandFilter;
import by.itacademy.keikom.taxi.services.IBrandServices;

@Service
public class BrandServicesImpl implements IBrandServices {

	@Autowired
	private IBrandDao dao;

	@Override
	public void remove(final Integer id) {
		dao.remove(id);

	}

	@Override
	public Brand save(final Brand brand) {
		if (brand.getId() == null) {
			dao.insert(brand);
		} else {
			dao.update(brand);
		}
		return brand;
	}

	@Override
	public List<Brand> getAll() {
		return dao.getAll();
	}

	@Override
	public Brand get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(BrandFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Brand> getAll(BrandFilter filter) {
		return dao.find(filter);
	}
}
