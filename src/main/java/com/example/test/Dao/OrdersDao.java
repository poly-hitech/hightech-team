package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.MyOrderList;
import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;
import com.example.test.Model.ResourceCategory;

public interface OrdersDao {

	Long buyResource(Orders orders);

	void saveOrdersDetails(OrdersDetails ordersDetails);

	List<MyOrderList> purchasedResources(Long userId);

	List<Long> getItemIdByLoginUser(Long userId);
}
