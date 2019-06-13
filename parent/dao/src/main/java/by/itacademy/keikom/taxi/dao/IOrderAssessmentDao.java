package by.itacademy.keikom.taxi.dao;

import java.util.List;

import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.dao.filter.OrderAssessmentFilter;

public interface IOrderAssessmentDao extends IHibernateDao<OrderAssessment, Integer> {

	Long count(OrderAssessmentFilter filter);

	List<OrderAssessment> find(OrderAssessmentFilter filter);

	List<OrderAssessment> commentsDriver(Integer id);

	String averageMark(List<OrderAssessment> list);
}
