package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.ICarOptionDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.filter.CarOptionFilter;

@Repository
public class CarOptionDaoImpl extends AbstractHibernateDaoImpl<CarOption, Integer> implements ICarOptionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarOptionDaoImpl.class);

	protected CarOptionDaoImpl() {
		super(CarOption.class);
	}

	@Override
	public Long count(CarOptionFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<CarOption> from = cq.from(CarOption.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<CarOption> find(CarOptionFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CarOption> cq = cb.createQuery(CarOption.class);
		Root<CarOption> from = cq.from(CarOption.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<CarOption> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}
}
