package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.web.dto.OrderAssessmentDTO;

@Component
public class OrderAssessmentFromDTOConverter implements Function<OrderAssessmentDTO, OrderAssessment> {

	@Override
	public OrderAssessment apply(OrderAssessmentDTO dto) {

		OrderAssessment dbModel = new OrderAssessment();

		dbModel.setOrderId(dto.getOrderId());
		dbModel.setAssessment(dto.getAssessment());
		dbModel.setComment(dto.getComment());

		CarOrder order = new CarOrder();
		order.setId(dto.getOrderId());
		dbModel.setOrder(order);

		return dbModel;
	}

}
