package by.itacademy.keikom.taxi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.ICarOrderDao;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder_;
import by.itacademy.keikom.taxi.dao.filter.CarOrderFilter;

@Repository
public class CarOrderDaoImpl extends AbstractHibernateDaoImpl<CarOrder, Integer> implements ICarOrderDao {

	protected CarOrderDaoImpl() {
		super(CarOrder.class);
	}

	@Override
	public Long count(CarOrderFilter filter) {

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<CarOrder> from = cq.from(CarOrder.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<CarOrder> find(CarOrderFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CarOrder> cq = cb.createQuery(CarOrder.class);
		Root<CarOrder> from = cq.from(CarOrder.class);
		cq.select(from);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<CarOrder> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public Integer getLatestOrderId() {

		final EntityManager em = getEntityManager();

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		final Root<CarOrder> from = cq.from(CarOrder.class);
		cq.select(from.get(CarOrder_.id));

		cq.orderBy(new OrderImpl(from.get(CarOrder_.id), false));

		final TypedQuery<Integer> q = em.createQuery(cq);
		q.setMaxResults(1);
		return q.getResultList().get(0);

	}

	@Override
	public List<CarOrder> ordersToClient(Integer id, String role) {

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CarOrder> orderOwnerCriteria = cb.createQuery(CarOrder.class);
		Root<CarOrder> ownerOrdertRoot = orderOwnerCriteria.from(CarOrder.class);
		orderOwnerCriteria.select(ownerOrdertRoot);
		orderOwnerCriteria.where(cb.equal(ownerOrdertRoot.get(role).get("id"), id));

		return em.createQuery(orderOwnerCriteria).getResultList();
	}

	@Override
	public Double revenue(List<CarOrder> list) {

		Double result = 0.0;

		for (CarOrder carOrder : list) {
			result += carOrder.getTotal();
		}
		return result;
	}

}
