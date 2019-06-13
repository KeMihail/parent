package by.itacademy.keikom.taxi.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.web.dto.OrderAssessmentDTO;

@Component
public class OrderAssessmentToDTOConverter implements Function<OrderAssessment, OrderAssessmentDTO> {

	@Override
	public OrderAssessmentDTO apply(OrderAssessment dbModel) {

		OrderAssessmentDTO dto = new OrderAssessmentDTO();
		dto.setOrderId(dbModel.getOrderId());
		dto.setOrderId(dbModel.getOrder().getId());
		dto.setAssessment(dbModel.getAssessment());
		dto.setComment(dbModel.getComment());
		return dto;
	}

}
