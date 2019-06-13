package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IDriverDao;
import by.itacademy.keikom.taxi.dao.dbmodel.Driver;
import by.itacademy.keikom.taxi.dao.enums.Role;
import by.itacademy.keikom.taxi.dao.filter.DriverFilter;
import by.itacademy.keikom.taxi.services.IDriverServices;
import by.itacademy.keikom.taxi.services.IUserServices;
import by.itacademy.keikom.taxi.services.mail.SendMailTLS;
import by.itacademy.keikom.taxi.services.security.PasswordGenerator;

@Service
public class DriverServicesImpl implements IDriverServices {

	@Autowired
	private IDriverDao dao;
	@Autowired
	private IUserServices userService;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Driver save(Driver driver) {

		driver.setDeleted(false);
		driver.setRole(Role.DRIVER);

		if (driver.getId() == null) {

			String password = PasswordGenerator
					.generatePassword(PasswordGenerator.ALPHA_CAPS + PasswordGenerator.NUMERIC);
			driver.setPassword(DigestUtils.md5Hex(password));
			SendMailTLS.sendMail(driver.getEmail(), password);
			dao.insert(driver);

		} else {
			dao.update(driver);
		}
		return driver;
	}

	@Override
	public List<Driver> getAll() {
		return dao.getAll();
	}

	@Override
	public Driver get(Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(DriverFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Driver> getAll(DriverFilter filter) {
		return dao.find(filter);
	}

	@Override
	public Driver getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public Driver loadByLogin(String email) {
		return dao.loadByLogin(email);
	}

	@Override
	public List<String> loadAllEmail() {
		return dao.loadAllEmail();
	}

	@Override
	public Boolean checkEmail(String email) {
		Boolean result = false;
		List<String> list = userService.loadAllEmail();

		for (String str : list) {
			if (str.equals(email)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
