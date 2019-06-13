package by.itacademy.keikom.taxi.dao;

import java.util.List;

public interface IHibernateDao<T, ID> {

	List<T> getAll();

	T get(final ID id);

	T insert(final T entity);

	T update(T entity);

	void remove(ID id);
}