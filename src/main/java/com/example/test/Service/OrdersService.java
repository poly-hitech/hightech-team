package com.example.test.Service;

import java.util.List;

import com.example.test.Model.Market;
import com.example.test.Model.ResourceShop;

public interface OrdersService {

	void buyResource(Long userId, Long itemId, List<ResourceShop> shop);

	List<Market> purchasedResources(Long userId);
}
