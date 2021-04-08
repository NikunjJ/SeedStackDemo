package com.order.domain.model.order;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.seedstack.business.domain.BaseValueObject;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItems extends BaseValueObject{

	@Id
	private String orderId;
	
	@Id
	private String itemId;
	
	private String itemName;
	
	private double grossAmount;
	
	private double taxAmount;
	
	private double discountAmount;

	private int quantity;
	
	private double totalAmount;// (Gross + Tax - Discount) * quantity
	
	private Date manufactureDate;
	
	private Date  expireDate;

	public OrderItems(String orderId, String itemId, String itemName, double grossAmount, double taxAmount, Date manufactureDate, Date expireDate, int quantity) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.grossAmount = grossAmount;
		this.taxAmount = taxAmount;
		this.manufactureDate = manufactureDate;
		this.expireDate = expireDate;
		this.quantity = quantity;
	}

	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
