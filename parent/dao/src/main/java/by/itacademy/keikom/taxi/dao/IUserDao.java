package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.filter.UserFilter;

public interface IUserDao extends IHibernateDao<User, Integer> {

	Long count(UserFilter filter);

	List<User> find(UserFilter filter);

	User loadByLogin(String email);

	List<String> loadAllEmail();

}
