package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.IModelDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.Model_;
import by.itacademy.keikom.taxi.dao.filter.ModelFilter;

@Repository
public class ModelDaoImpl extends AbstractHibernateDaoImpl<Model, Integer> implements IModelDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelDaoImpl.class);

	protected ModelDaoImpl() {
		super(Model.class);
	}

	@Override
	public Long count(ModelFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Model> from = cq.from(Model.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Model> find(ModelFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Model> cq = cb.createQuery(Model.class);
		Root<Model> from = cq.from(Model.class);
		cq.select(from);
		// set sort params

		from.fetch(Model_.brand, JoinType.LEFT);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Model> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public Model getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Model> cq = cb.createQuery(Model.class);
		final Root<Model> from = cq.from(Model.class);
		cq.select(from);

		from.fetch(Model_.brand, JoinType.LEFT);
		cq.where(cb.equal(from.get(Model_.id), id));

		// cq.distinct(true); only if you join 2MANY like 'catalogs'

		final TypedQuery<Model> q = em.createQuery(cq);

		List<Model> resultList = q.getResultList();
		if (resultList.size() != 1) {
			throw new RuntimeException("unexpected result size:" + resultList.size());

		}
		return resultList.get(0);
	}
}
