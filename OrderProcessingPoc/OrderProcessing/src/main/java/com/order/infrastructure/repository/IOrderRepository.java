package com.order.infrastructure.repository;

import java.util.stream.Stream;

import org.seedstack.business.domain.Repository;
import org.seedstack.seed.Bind;

import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderId;

public interface IOrderRepository extends Repository<Order, OrderId>{
	
	default Stream<Order> getAllOrders(){
		
		return get(getSpecificationBuilder().of(Order.class).all().build());
	}
}
