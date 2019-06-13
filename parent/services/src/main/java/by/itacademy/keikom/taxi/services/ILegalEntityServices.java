package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.filter.LegalEntityFilter;

public interface ILegalEntityServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	LegalEntity save(LegalEntity legalEntity);

	List<LegalEntity> getAll();

	LegalEntity get(Integer id);

	Long getCount(LegalEntityFilter filter);

	List<LegalEntity> getAll(LegalEntityFilter filter);
}
