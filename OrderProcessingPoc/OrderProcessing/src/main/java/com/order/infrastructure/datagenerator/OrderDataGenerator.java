package com.order.infrastructure.datagenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.LifecycleListener;
import org.seedstack.seed.transaction.Transactional;

import com.google.inject.Inject;
import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderId;
import com.order.domain.model.order.OrderItems;
import com.order.domain.model.order.OrderStatus;

public class OrderDataGenerator implements LifecycleListener {

	@Inject
//	@InMemory //Commented as JPA is included
	@Jpa
	private Repository<Order, OrderId> orderRepository;

	@Override
	@JpaUnit("myUnit")
	@Transactional
	public void started() {

		System.out.println("In memory loaded.....");

		//Order 1
		Order order = new Order(new OrderId("O001"),1, LocalDateTime.now(), OrderStatus.CONFIRMED);
		List<OrderItems> items1 = new ArrayList<>();
		items1.add(new OrderItems(order.getId().getOrderId(), "I1", "TV", 10000, 2800, LocalDate.now(), LocalDate.of(2022, 12, 31),2));
		items1.add(new OrderItems(order.getId().getOrderId(), "I2", "TV Cover", 1000, 100, LocalDate.now(), LocalDate.of(2022, 12, 31),2));
		order.setItems(items1);
		
		//Order 2
		Order order2 = new Order(new OrderId("O002"),1, LocalDateTime.now(), OrderStatus.CANCELLED);
		List<OrderItems> items2 = new ArrayList<>();
		items2.add(new OrderItems(order2.getId().getOrderId(), "I1", "Mobile", 5000, 580, LocalDate.now(), LocalDate.of(2022, 12, 31),5));
		order2.setItems(items2);
		
		//Order 3
		Order order3 = new Order(new OrderId("O003"),1, LocalDateTime.now(), OrderStatus.BOOKED);
		List<OrderItems> items3 = new ArrayList<>();
		items3.add(new OrderItems(order3.getId().getOrderId(), "I1", "Laptop", 50000, 1000, LocalDate.now(), LocalDate.of(2022, 12, 31),1));
		order3.setItems(items3);
		
		orderRepository.addOrUpdate(order);
		orderRepository.addOrUpdate(order2);
		orderRepository.addOrUpdate(order3);

	}

}
