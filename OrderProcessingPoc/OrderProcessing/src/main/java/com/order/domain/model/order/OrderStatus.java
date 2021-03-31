package com.order.domain.model.order;

import org.seedstack.business.domain.ValueObject;

public enum OrderStatus implements ValueObject{

	BOOKED, CANCELLED, CONFIRMED
}
