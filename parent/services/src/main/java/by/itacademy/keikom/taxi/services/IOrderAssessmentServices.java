package by.itacademy.keikom.taxi.services;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.dao.filter.OrderAssessmentFilter;

public interface IOrderAssessmentServices {

	@Transactional
	void remove(Integer id);

	@Transactional
	OrderAssessment save(OrderAssessment orderAssessment);

	List<OrderAssessment> getAll();

	OrderAssessment get(Integer id);

	Long getCount(OrderAssessmentFilter filter);

	List<OrderAssessment> getAll(OrderAssessmentFilter filter);

	List<OrderAssessment> commentsDriver(Integer id);

	String averageMark(List<OrderAssessment> list);
}
