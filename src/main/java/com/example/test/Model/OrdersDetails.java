package com.example.test.Model;

//주문내역
public class OrdersDetails {
	private Long ordersDetailsId;
	private Long itemId;
	private Long amount = 1L;
	private Long ordersId;
	private Counting counting;
	
	public Long getOrdersDetailsId() {
		return ordersDetailsId;
	}
	public void setOrdersDetailsId(Long ordersDetailsId) {
		this.ordersDetailsId = ordersDetailsId;
	}
	public Counting getCounting() {
		return counting;
	}
	public void setCounting(Counting counting) {
		this.counting = counting;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

}
