package com.order.interfaces.rest.order;

import java.util.Date;
import java.util.List;

import org.seedstack.business.assembler.AggregateId;
import org.seedstack.business.assembler.DtoOf;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderId;
import com.order.domain.model.order.OrderStatus;

@DtoOf(Order.class)
public class OrderDto {

	private OrderId orderId;

	private int orderVersion;

//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
	private Date createdDateTime;

	private double orderAmount;

	private OrderStatus orderStatus;

	private List<OrderItemsDto> items;

	public OrderDto() {
	}

	public List<OrderItemsDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemsDto> items) {
		this.items = items;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	@AggregateId
	public OrderId getOrderId() {
		return orderId;
	}

	public void setOrderId(OrderId orderId) {
		this.orderId = orderId;
	}

	public int getOrderVersion() {
		return orderVersion;
	}

	public void setOrderVersion(int orderVersion) {
		this.orderVersion = orderVersion;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
