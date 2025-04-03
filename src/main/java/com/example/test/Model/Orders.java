package com.example.test.Model;

import java.time.LocalDate;

//주문
public class Orders {
	private Long ordersId;
	private LocalDate orderDate;
	private String ordersUser;
	private Long userId;
	
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrdersUser() {
		return ordersUser;
	}
	public void setOrdersUser(String ordersUser) {
		this.ordersUser = ordersUser;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
