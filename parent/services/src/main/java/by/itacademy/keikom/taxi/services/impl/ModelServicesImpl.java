package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IModelDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.filter.ModelFilter;
import by.itacademy.keikom.taxi.services.IModelServices;

@Service
public class ModelServicesImpl implements IModelServices {

	@Autowired
	private IModelDao dao;

	@Override
	public void remove(final Integer id) {
		dao.remove(id);

	}

	@Override
	public Model save(final Model model) {
		if (model.getId() == null) {
			dao.insert(model);
		} else {
			dao.update(model);
		}
		return model;
	}

	@Override
	public List<Model> getAll() {
		return dao.getAll();
	}

	@Override
	public Model get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(ModelFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Model> getAll(ModelFilter filter) {
		return dao.find(filter);
	}

	@Override
	public Model getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}
}
