package by.itacademy.keikom.taxi.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import by.itacademy.keikom.taxi.dao.IHibernateDao;
import by.itacademy.keikom.taxi.dao.filter.AbstractFilter;

public class AbstractHibernateDaoImpl<T, ID> implements IHibernateDao<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	private final Class<T> entityClass;

	protected AbstractHibernateDaoImpl(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public List<T> getAll() {
		final CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
		query.from(getEntityClass());
		final List<T> lst = entityManager.createQuery(query).getResultList();
		return lst;
	}

	@Override
	public T get(final ID id) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public T insert(final T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		entity = entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public void remove(final ID id) {
		entityManager.createQuery(String.format("delete from %s e where e.id = :id", entityClass.getSimpleName()))
				.setParameter("id", id).executeUpdate();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected void setPaging(final AbstractFilter filter, final TypedQuery<? extends Serializable> q) {
		if ((filter.getOffset() != null) && (filter.getLimit() != null)) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}
	}
}