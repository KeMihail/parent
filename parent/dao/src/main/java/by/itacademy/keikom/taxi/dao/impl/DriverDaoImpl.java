package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.IDriverDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.dbmodel.Driver_;
import by.itacademy.keikom.taxi.dao.filter.DriverFilter;

@Repository
public class DriverDaoImpl extends AbstractHibernateDaoImpl<Driver, Integer> implements IDriverDao {

	protected DriverDaoImpl() {
		super(Driver.class);
	}

	@Override
	public Long count(DriverFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Driver> from = cq.from(Driver.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Driver> find(DriverFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
		Root<Driver> from = cq.from(Driver.class);
		cq.select(from);

		// from.fetch(Driver_.car, JoinType.LEFT);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Driver> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public Driver getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
		final Root<Driver> from = cq.from(Driver.class);
		cq.select(from);

		from.fetch(Driver_.car, JoinType.LEFT);
		cq.where(cb.equal(from.get(Driver_.id), id));

		// cq.distinct(true); only if you join 2MANY like 'catalogs'

		final TypedQuery<Driver> q = em.createQuery(cq);

		List<Driver> resultList = q.getResultList();
		if (resultList.size() != 1) {
			throw new RuntimeException("unexpected result size:" + resultList.size());

		}
		return resultList.get(0);
	}

	@Override
	public Driver loadByLogin(String email) {
		EntityManager em = getEntityManager();
		Driver driver = em.createQuery("select d from Driver d where d.email = :email", Driver.class)
				.setParameter("email", email).getSingleResult();
		return driver;
	}

	@Override
	public List<String> loadAllEmail() {
		EntityManager em = getEntityManager();

		return em.createQuery("select d.email from Driver d", String.class).getResultList();
	}
}
