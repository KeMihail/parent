package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.services.security.PasswordGenerator;

import by.itacademy.keikom.taxi.dao.IUserDao;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.filter.UserFilter;
import by.itacademy.keikom.taxi.services.IDriverServices;
import by.itacademy.keikom.taxi.services.IUserServices;
import by.itacademy.keikom.taxi.services.mail.SendMailTLS;

@Service
public class UserServicesImpl extends AbstractServicesImpl implements IUserServices {

	@Autowired
	private IUserDao dao;
	@Autowired
	private IDriverServices driverDao;

	@Override
	public void remove(final Integer id) {
		dao.remove(id);

	}

	@Override
	public User save(final User user) {

		user.setDeleted(false);
		if (user.getId() == null) {

			String password = PasswordGenerator
					.generatePassword(PasswordGenerator.ALPHA_CAPS + PasswordGenerator.NUMERIC);
			SendMailTLS.sendMail(user.getEmail(), password);
			user.setPassword(DigestUtils.md5Hex(password));

			dao.insert(user);
		} else {
			dao.update(user);
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		return dao.getAll();
	}

	@Override
	public User get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(UserFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<User> getAll(UserFilter filter) {
		return dao.find(filter);
	}

	@Override
	public User loadByLogin(String email) {
		return dao.loadByLogin(email);
	}

	@Override
	public List<String> loadAllEmail() {
		return dao.loadAllEmail();
	}

	@Override
	public Boolean checkEmail(String email) {

		Boolean result = false;
		List<String> list = driverDao.loadAllEmail();

		for (String str : list) {
			if (str.equals(email)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
