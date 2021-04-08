package com.order.domain.services.order;

import java.util.List;

import org.seedstack.business.Service;

import com.order.domain.model.order.Order;
import com.order.interfaces.rest.order.OrderDto;

@Service
public interface OrderService {

	public List<Order> getAllOrders();	
	
	public Order getOrder(String orderId, int orderVersion);
	
	public void deleteOrder(String orderId);

	public void createOrder(Order order);
}
