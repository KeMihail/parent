package by.itacademy.keikom.taxi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.keikom.taxi.dao.IOrderAssessmentDao;
import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.dao.filter.OrderAssessmentFilter;
import by.itacademy.keikom.taxi.services.IOrderAssessmentServices;

@Service
public class OrderAssessmentServicesImpl implements IOrderAssessmentServices {

	@Autowired
	private IOrderAssessmentDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public OrderAssessment save(OrderAssessment orderAssessment) {

		dao.insert(orderAssessment);
		return orderAssessment;
	}

	@Override
	public List<OrderAssessment> getAll() {
		return dao.getAll();
	}

	@Override
	public OrderAssessment get(Integer id) {
		return dao.get(id);
	}

	@Override
	public Long getCount(OrderAssessmentFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<OrderAssessment> getAll(OrderAssessmentFilter filter) {
		return dao.find(filter);
	}

	@Override
	public List<OrderAssessment> commentsDriver(Integer id) {
		return dao.commentsDriver(id);
	}

	@Override
	public String averageMark(List<OrderAssessment> list) {
		return dao.averageMark(list);
	}
}
