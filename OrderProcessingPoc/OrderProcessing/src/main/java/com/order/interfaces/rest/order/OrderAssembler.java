package com.order.interfaces.rest.order;

import java.util.ArrayList;
import java.util.List;

import org.seedstack.business.assembler.BaseAssembler;
import org.seedstack.seed.Bind;

import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderItems;

@Bind
public class OrderAssembler extends BaseAssembler<Order, OrderDto>{

	@Override
	public void mergeAggregateIntoDto(Order order, OrderDto orderDto) {
	
		orderDto.setOrderId(order.getId());
		orderDto.setCreatedDateTime(order.getCreatedDateTime());
		orderDto.setOrderAmount(order.getOrderAmount());
		orderDto.setOrderStatus(order.getOrderStatus());
		orderDto.setOrderVersion(order.getOrderVersion());
	
		List<OrderItemsDto> itemsDto = new ArrayList<>(); 
		
		order.getItems().stream().forEach(
			item ->{
				
				//Create new instance of OrderItemDto and copy values from Order Item
				OrderItemsDto iDto = new OrderItemsDto();
				iDto.setOrderId(item.getOrderId());
				iDto.setItemId(item.getItemId());
				iDto.setGrossAmount(item.getGrossAmount());
				iDto.setTaxAmount(item.getTaxAmount());
				iDto.setDiscountAmount(item.getDiscountAmount());
				iDto.setManufactureDate(item.getManufactureDate());
				iDto.setExpireDate(item.getExpireDate());
				iDto.setItemName(item.getItemName());
				iDto.setTotalAmount(item.getTotalAmount());
				iDto.setQuantity(item.getQuantity());
				itemsDto.add(iDto);
			}
		);
		
		orderDto.setItems(itemsDto);
	}
	
	@Override
	public void mergeDtoIntoAggregate(OrderDto orderDto, Order order) {

		order.setOrderId(orderDto.getOrderId());
		order.setOrderVersion(orderDto.getOrderVersion());
		order.setCreatedDateTime(orderDto.getCreatedDateTime());
		order.setOrderAmount(orderDto.getOrderAmount());
		order.setOrderStatus(orderDto.getOrderStatus());
		
		List<OrderItems> orderItems = new ArrayList<>(); 
		
		orderDto.getItems().stream().forEach(
			item ->{
				
				//Create new instance of OrderItem and copy values from Order Item Dto
				OrderItems ito = new OrderItems();
				ito.setOrderId(item.getOrderId());
				ito.setItemId(item.getItemId());
				ito.setGrossAmount(item.getGrossAmount());
				ito.setTaxAmount(item.getTaxAmount());
				ito.setDiscountAmount(item.getDiscountAmount());
				ito.setManufactureDate(item.getManufactureDate());
				ito.setExpireDate(item.getExpireDate());
				ito.setItemName(item.getItemName());
				ito.setTotalAmount(item.getTotalAmount());
				ito.setQuantity(item.getQuantity());
				orderItems.add(ito);
			}
		);
		
		order.setItems(orderItems);
	}
}

