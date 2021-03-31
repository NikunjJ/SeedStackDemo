package com.order.domain.policy;

import org.seedstack.business.Service;
import org.seedstack.business.domain.DomainPolicy;

import com.order.domain.model.order.Order;

/*
 * If Order is cancelled then applied discount on item if any should be negate
 * If Order is confirmed then if item total amount > 10,000 then discount = 2000 @ item level and default = 100
 */
@DomainPolicy
@Service
public interface DiscountPolicy {

	public String CANCELLED_ORDER = "CANCELLED";
	
	public String CONFIRMED_ORDER = "CONFIRMED";
	
	public String BOOKED_ORDER = "BOOKED";
	
	public void discountAmountPolicy(Order order);
}
