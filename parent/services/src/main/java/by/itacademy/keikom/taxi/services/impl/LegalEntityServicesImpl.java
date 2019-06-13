package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.ILegalEntityDao;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.filter.LegalEntityFilter;
import by.itacademy.keikom.taxi.services.ILegalEntityServices;

@Service
public class LegalEntityServicesImpl extends AbstractServicesImpl implements ILegalEntityServices {

	@Autowired
	private ILegalEntityDao dao;

	/*
	 * if (!validateEmailAddress(legalEntity.getEmail())) {
	 * legalEntity.setEmail(null); }
	 * 
	 * if (!validatePhoneNumber(legalEntity.getPhone_number())) { throw new
	 * NotValidPhoneNumberException(); }
	 */

	@Override
	public void remove(final Integer id) {
		dao.remove(id);

	}

	@Override
	public LegalEntity save(final LegalEntity legalEntity) {
		if (legalEntity.getId() == null) {
			dao.insert(legalEntity);
		} else {
			dao.update(legalEntity);
		}
		return legalEntity;
	}

	@Override
	public List<LegalEntity> getAll() {
		return dao.getAll();
	}

	@Override
	public LegalEntity get(final Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(LegalEntityFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<LegalEntity> getAll(LegalEntityFilter filter) {
		return dao.find(filter);
	}
}
