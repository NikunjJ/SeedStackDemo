package com.order.domain.policy;

import javax.inject.Named;

import org.seedstack.seed.Bind;

import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderStatus;

@Named(DiscountPolicy.CANCELLED_ORDER)
public class CancelledDiscountPolicy implements DiscountPolicy{

	@Override
	public void discountAmountPolicy(Order order) {
		
		//if item is cancelled then applied discount on item if any should be negate 
		if(order.getOrderStatus() == OrderStatus.CANCELLED)
		{
			order.getItems().stream().forEach(item -> item.setDiscountAmount(-1*item.getDiscountAmount()));
		}
	
		//re-calculate amounts
		order.deriveAmount();
		
	}

	
}
