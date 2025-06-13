package com.example.test.Service;

import com.example.test.Model.Orders;
import com.example.test.Model.ResourceShop;

public interface OrdersService {

	void buyResource(Long userId, Long itemId, Orders orders, ResourceShop shop);

}
