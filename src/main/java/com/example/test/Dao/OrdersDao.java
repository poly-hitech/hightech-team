package com.example.test.Dao;

import java.util.List;
import java.util.Map;

import com.example.test.Model.MyOrderList;
import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;

public interface OrdersDao {

	Long buyResource(Orders orders);

	void saveOrdersDetails(OrdersDetails ordersDetails);

	List<MyOrderList> purchasedResources(Long userId);

	List<Long> getItemIdByLoginUser(Long userId);

	OrdersDetails getOrdersDetailsByUserIdAndItemId(Map<String, Object> params);

	List<MyOrderList> mySalesList(String nickname);
}
