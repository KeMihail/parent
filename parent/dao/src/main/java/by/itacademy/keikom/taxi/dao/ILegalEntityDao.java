package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.filter.LegalEntityFilter;

public interface ILegalEntityDao extends IHibernateDao<LegalEntity, Integer> {

	Long count(LegalEntityFilter filter);

	List<LegalEntity> find(LegalEntityFilter filter);
}
