package com.example.test.Dao;

import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;

public interface OrdersDao {

	Long buyResource(Orders orders);

	void saveOrdersDetails(OrdersDetails ordersDetails);

}
