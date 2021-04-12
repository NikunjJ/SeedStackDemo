package com.order.domain.events;

import org.seedstack.business.domain.DomainEventHandler;

import com.order.domain.model.order.Order;

public class MessagePublishedEventHandler implements DomainEventHandler<MessagePublishedEvent>{

	@Override
	public void onEvent(MessagePublishedEvent event) {
		
		Order order = event.getOrder();
		
		System.out.println("Order Published: "+order.getOrderId());
		//TODO: Kafka Publish pending
	}

	@Override
	public Class<MessagePublishedEvent> getEventClass() {
		// TODO Auto-generated method stub
		return MessagePublishedEvent.class;
	}

	
}
