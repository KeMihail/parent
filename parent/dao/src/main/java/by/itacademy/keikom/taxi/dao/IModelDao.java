package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.filter.ModelFilter;

public interface IModelDao extends IHibernateDao<Model, Integer> {

	Long count(ModelFilter filter);

	List<Model> find(ModelFilter filter);

	Model getFullInfo(Integer id);

}
