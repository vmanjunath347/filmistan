package com.priya.rightcare.DTO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_details")
public class CustomerOrderResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int OrderId;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_price")
	private double productPrice;

	@Column(name = "quantity")
	private int quantity;

	public CustomerOrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerOrderResponse( int customerId, String productName, double productPrice,
			int quantity) {
		super();
		
		this.customerId = customerId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
