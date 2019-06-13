package by.itacademy.keikom.taxi.services;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.filter.ModelFilter;

public interface IModelServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	Model save(Model model);

	List<Model> getAll();

	Model get(Integer id);

	Long getCount(ModelFilter filter);

	List<Model> getAll(ModelFilter filter);

	Model getFullInfo(Integer id);
}
