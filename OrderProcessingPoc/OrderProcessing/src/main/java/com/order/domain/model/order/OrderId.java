package com.order.domain.model.order;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.seedstack.business.domain.BaseValueObject;

@Embeddable
public class OrderId extends BaseValueObject {

	private static final long serialVersionUID = 1L;

//	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String orderId;

	public OrderId() {}

	public OrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

}
