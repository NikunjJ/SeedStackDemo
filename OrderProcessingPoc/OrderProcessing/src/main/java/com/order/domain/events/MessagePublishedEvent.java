package com.order.domain.events;

import org.seedstack.business.domain.BaseDomainEvent;

import com.order.domain.model.order.Order;

public class MessagePublishedEvent extends BaseDomainEvent{

	private Order order;
	
	public MessagePublishedEvent(Order order)
	{
		this.order = order;
	}
	
	public Order getOrder() {
		return this.order;
	}
}
