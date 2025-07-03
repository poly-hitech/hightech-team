package com.example.test.Service;

import java.util.List;

import com.example.test.Model.MyOrderList;
import com.example.test.Model.OrdersDetails;
import com.example.test.Model.ResourceShop;

public interface OrdersService {

	void buyResource(Long userId, Long itemId, List<ResourceShop> shop);

	List<MyOrderList> purchasedResources(Long userId);

    List<Long> getItemIdByLoginUser(Long userId);

	OrdersDetails getOrdersDetailsByUserIdAndItemId(Long userId, Long itemId);
}
