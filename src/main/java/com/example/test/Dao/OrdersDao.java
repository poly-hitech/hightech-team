package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.Market;
import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;

public interface OrdersDao {

	Long buyResource(Orders orders);

	void saveOrdersDetails(OrdersDetails ordersDetails);

	List<Market> purchasedResources(Long userId);
}
