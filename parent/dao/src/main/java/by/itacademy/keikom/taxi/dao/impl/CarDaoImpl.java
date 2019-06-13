package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.ICarDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car_;
import by.itacademy.keikom.taxi.dao.filter.CarFilter;

@Repository
public class CarDaoImpl extends AbstractHibernateDaoImpl<Car, Integer> implements ICarDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarDaoImpl.class);

	protected CarDaoImpl() {
		super(Car.class);
	}

	@Override
	public Long count(CarFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Car> from = cq.from(Car.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Car> find(CarFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Car> cq = cb.createQuery(Car.class);
		Root<Car> from = cq.from(Car.class);
		cq.select(from);
		// set sort params

		from.fetch(Car_.model, JoinType.LEFT);
		from.fetch(Car_.legalEntity, JoinType.LEFT);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Car> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}
}
