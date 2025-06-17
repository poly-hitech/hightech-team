package com.example.test.Model;

//주문내역
public class OrdersDetails {
	private Long ordersDetailsId;
	private Long itemId;
	private int amount = 1;
	private Long detailitemPrice;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Long getDetailitemPrice() {
		return detailitemPrice;
	}
	public void setDetailitemPrice(Long detailitemPrice) {
		this.detailitemPrice = detailitemPrice;
	}

}
