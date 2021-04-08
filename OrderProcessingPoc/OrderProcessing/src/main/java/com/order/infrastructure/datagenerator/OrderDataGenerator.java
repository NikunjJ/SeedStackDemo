package com.order.infrastructure.datagenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seedstack.business.domain.DomainRegistry;
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
import com.order.domain.policy.DiscountPolicy;

public class OrderDataGenerator implements LifecycleListener {

	@Inject
//	@InMemory //Commented as JPA is included
	@Jpa
	private Repository<Order, OrderId> orderRepository;

	private DomainRegistry domainRegistory;
	
	@Inject
	public OrderDataGenerator(DomainRegistry domainRegistory)
	{
		this.domainRegistory = domainRegistory;
	}
	
	@Override
	@JpaUnit("myUnit")
	@Transactional
	public void started() {

		System.out.println("In memory loaded.....");

		//Order 1
		Order order = new Order(new OrderId("O001"),1, new Date(), OrderStatus.CONFIRMED);
		List<OrderItems> items1 = new ArrayList<>();
		items1.add(new OrderItems(order.getId().getOrderId(), "I1", "TV", 10000, 2800, new Date(), new Date(2022, 12, 31),2));
		items1.add(new OrderItems(order.getId().getOrderId(), "I2", "TV Cover", 1000, 100, new Date(), new Date(2022, 12, 31),2));
		order.setItems(items1);
		
		//Order 2
		Order order2 = new Order(new OrderId("O002"),1, new Date(), OrderStatus.CANCELLED);
		List<OrderItems> items2 = new ArrayList<>();
		items2.add(new OrderItems(order2.getId().getOrderId(), "I1", "Mobile", 5000, 580, new Date(), new Date(2022, 12, 31),5));
		order2.setItems(items2);
		
		//Order 3
		Order order3 = new Order(new OrderId("O003"),1, new Date(), OrderStatus.BOOKED);
		List<OrderItems> items3 = new ArrayList<>();
		items3.add(new OrderItems(order3.getId().getOrderId(), "I1", "Laptop", 50000, 1000, new Date(), new Date(2022, 12, 31),1));
		order3.setItems(items3);
		

		DiscountPolicy policy = domainRegistory.getPolicy(DiscountPolicy.class,order.getOrderStatus().name());
		policy.discountAmountPolicy(order);
		
		DiscountPolicy policy2 = domainRegistory.getPolicy(DiscountPolicy.class,order2.getOrderStatus().name());
		policy2.discountAmountPolicy(order2);
		
		DiscountPolicy policy3 = domainRegistory.getPolicy(DiscountPolicy.class,order3.getOrderStatus().name());
		policy3.discountAmountPolicy(order3);
		
		orderRepository.addOrUpdate(order);
		orderRepository.addOrUpdate(order2);
		orderRepository.addOrUpdate(order3);

	}

}
