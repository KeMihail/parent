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

import by.itacademy.keikom.taxi.dao.ILegalEntityDao;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.filter.LegalEntityFilter;

@Repository
public class LegalEntityDaoImpl extends AbstractHibernateDaoImpl<LegalEntity, Integer> implements ILegalEntityDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityDaoImpl.class);

	protected LegalEntityDaoImpl() {
		super(LegalEntity.class);
	}

	@Override
	public Long count(LegalEntityFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LegalEntity> from = cq.from(LegalEntity.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<LegalEntity> find(LegalEntityFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LegalEntity> cq = cb.createQuery(LegalEntity.class);
		Root<LegalEntity> from = cq.from(LegalEntity.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<LegalEntity> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
