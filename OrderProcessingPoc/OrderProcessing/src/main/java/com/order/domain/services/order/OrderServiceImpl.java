package com.order.domain.services.order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.business.specification.dsl.SpecificationBuilder;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.transaction.Transactional;

import com.google.inject.Inject;
import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderId;
import com.order.domain.policy.DiscountPolicy;
import com.order.infrastructure.repository.IOrderRepository;

@Bind
public class OrderServiceImpl implements OrderService {
	
	@Inject
	private IOrderRepository orderRepository;
	
	private DomainRegistry domainRegistory;
	
	@Inject
	public OrderServiceImpl(DomainRegistry domainRegistory)
	{
		this.domainRegistory = domainRegistory;
	}
	
	@Override
	@JpaUnit("myUnit")
	@Transactional
	public List<Order> getAllOrders() {

		List<Order> orders = orderRepository.getAllOrders().collect(Collectors.toList());
		
		orders.stream().forEach(order -> {
		
			//apply policy
			DiscountPolicy policy = domainRegistory.getPolicy(DiscountPolicy.class, order.getOrderStatus().name());
			policy.discountAmountPolicy(order);
				
		});
		
		
		return orders;
	}

	@Override
	@JpaUnit("myUnit")
	@Transactional
	public Order getOrder(String orderId, int orderVersion) {
	
		SpecificationBuilder specificationBuilder = orderRepository.getSpecificationBuilder();
		
		Stream<Order> stream = orderRepository.get(specificationBuilder.of(Order.class)
				.property("orderId").equalTo(new OrderId(orderId))
				.and()
				.property("orderVersion").equalTo(orderVersion)
				.build());
		
		Order order = stream.findFirst().get();
		
		//apply policy
		DiscountPolicy policy = domainRegistory.getPolicy(DiscountPolicy.class,order.getOrderStatus().name());
		policy.discountAmountPolicy(order);
		
		return order;
	}

	@Override
	@JpaUnit("myUnit")
	@Transactional
	public void deleteOrder(String orderId) {

		this.orderRepository.remove(new OrderId(orderId));
	}
}
