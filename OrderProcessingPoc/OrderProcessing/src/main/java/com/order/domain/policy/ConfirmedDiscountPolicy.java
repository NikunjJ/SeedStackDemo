package com.order.domain.policy;

import javax.inject.Named;

import org.seedstack.seed.Bind;

import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderStatus;

@Named(DiscountPolicy.CONFIRMED_ORDER)
public class ConfirmedDiscountPolicy implements DiscountPolicy{

	@Override
	public void discountAmountPolicy(Order order) {
	
		
		if(order.getOrderStatus() == OrderStatus.CONFIRMED)
		{
			//calculate total order amount and item level amount
			order.deriveAmount();
			
			//if item total amount > 10,000 then discount = 2000 @ item level and default = 100
			order.getItems().stream().forEach(item -> {
			
				double totalAtItemLevel = item.getTotalAmount();
				
				if(totalAtItemLevel>10000)
				{
					//2000 RS per Item Qty
					item.setDiscountAmount(500);	
				}
				else
				{
					//default = 100 RS
					item.setDiscountAmount(100);
				}
			
			});
			
			//re-calculate amounts
			order.deriveAmount();
			
		}
		
	}

	
}
