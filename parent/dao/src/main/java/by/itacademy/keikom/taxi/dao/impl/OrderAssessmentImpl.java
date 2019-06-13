package by.itacademy.keikom.taxi.dao.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.keikom.taxi.dao.IOrderAssessmentDao;
import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.dao.filter.OrderAssessmentFilter;

@Repository
public class OrderAssessmentImpl extends AbstractHibernateDaoImpl<OrderAssessment, Integer>
		implements IOrderAssessmentDao {

	protected OrderAssessmentImpl() {
		super(OrderAssessment.class);
	}

	@Override
	public Long count(OrderAssessmentFilter filter) {

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<OrderAssessment> from = cq.from(OrderAssessment.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<OrderAssessment> find(OrderAssessmentFilter filter) {

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderAssessment> cq = cb.createQuery(OrderAssessment.class);
		Root<OrderAssessment> from = cq.from(OrderAssessment.class);
		cq.select(from);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<OrderAssessment> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public List<OrderAssessment> commentsDriver(Integer id) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderAssessment> criteria = cb.createQuery(OrderAssessment.class);
		Root<OrderAssessment> root = criteria.from(OrderAssessment.class);
		criteria.select(root);
		criteria.where(cb.equal(root.get("order").get("driver").get("id"), id));

		return em.createQuery(criteria).getResultList();
	}

	@Override
	public String averageMark(List<OrderAssessment> list) {
		Double averageMark = 0.0;
		Integer counter = 0;
		for (OrderAssessment orderAssessment : list) {
			averageMark += (double) +orderAssessment.getAssessment();
			counter++;
		}

		DecimalFormat newFormat = new DecimalFormat("#.##");
		return newFormat.format(averageMark / counter);
	}
}
