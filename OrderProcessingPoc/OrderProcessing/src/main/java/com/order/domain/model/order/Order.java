package com.order.domain.model.order;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.seedstack.business.domain.BaseAggregateRoot;

@Entity
@Table(name = "ORDER_MASTER")
public class Order extends BaseAggregateRoot<OrderId> {

	@EmbeddedId
	private OrderId orderId;

	private int orderVersion;
	
	private Date createdDateTime;

	private double orderAmount;

	private OrderStatus orderStatus;

	//1 Order - Many Items
	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItems> items;
	
	public Order() {
	}

	public Order(OrderId id, int orderVersion, Date createdDateTime, OrderStatus orderStatus) {
		super();
		this.orderId = id;
		this.orderVersion = orderVersion;
		this.createdDateTime = createdDateTime;
		this.orderStatus = orderStatus;
	}

	public List<OrderItems> getItems() {
		return items;
	}

	public void setItems(List<OrderItems> items) {
		this.items = items;
		deriveAmount();
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

	@Override
	public OrderId getId() {

		return this.orderId;
	}
	
	public OrderId getOrderId() {

		return this.orderId;
	}

	public int getOrderVersion() {
		return orderVersion;
	}

	public void setOrderVersion(int orderVersion) {
		this.orderVersion = orderVersion;
	}

	public void setOrderId(OrderId orderId) {
		this.orderId = orderId;
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
	
	// calcualte item wise total amount and order amount
	public void deriveAmount() {
		
		if(this.items!=null && !this.items.isEmpty())
		{
			this.items.stream().forEach(i -> {
				
				double grossAmount = i.getGrossAmount();
				double taxAmount = i.getTaxAmount();
				double discountAmount = i.getDiscountAmount();
				int quantity = i.getQuantity();
				
				double total = quantity * (grossAmount+taxAmount-discountAmount);
				
				i.setTotalAmount(total);
				
				this.orderAmount += total;
			});
		}
		else
		{
			this.orderAmount = 0;
		}
	}

}
