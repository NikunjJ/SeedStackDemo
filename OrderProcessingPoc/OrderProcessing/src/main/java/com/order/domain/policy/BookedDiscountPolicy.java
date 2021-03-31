package com.order.domain.policy;

import javax.inject.Named;

import org.seedstack.seed.Bind;

import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderStatus;

@Named(DiscountPolicy.BOOKED_ORDER)
public class BookedDiscountPolicy implements DiscountPolicy {

	//There is no rules applied for Booked order, just recalculating amounts
	@Override
	public void discountAmountPolicy(Order order) {

		// re-calculate amounts
		order.deriveAmount();

	}

}
