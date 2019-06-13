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

import by.itacademy.keikom.taxi.dao.IUserDao;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.filter.UserFilter;

@Repository
public class UserDaoImpl extends AbstractHibernateDaoImpl<User, Integer> implements IUserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	protected UserDaoImpl() {
		super(User.class);
	}

	@Override
	public Long count(UserFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<User> from = cq.from(User.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<User> find(UserFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> from = cq.from(User.class);
		cq.select(from);
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<User> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public User loadByLogin(String email) {
		EntityManager em = getEntityManager();
		User user = em.createQuery("select u from User u where u.email = :email", User.class)
				.setParameter("email", email).getSingleResult();
		return user;
	}

	@Override
	public List<String> loadAllEmail() {

		EntityManager em = getEntityManager();
		return em.createQuery("select u.email from User u", String.class).getResultList();
	}
}
