package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.Market;
import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	SqlSession sql;
	
	//주문정보를 저장하고 주문번호를 리턴 받음
	@Override
	public Long buyResource(Orders orders) {
		sql.insert("orders.buyResource", orders);
		return orders.getOrdersId();
	}

	@Override
	public void saveOrdersDetails(OrdersDetails ordersDetails) {
		sql.insert("ordersDetails.saveOrdersDetails", ordersDetails);
	}
	
	//로그인 한 사용자가 구매한 리소스 리스트
	@Override
	public List<Market> purchasedResources(Long userId) {
		return sql.selectList("resourceShop.purchasedResources", userId);
	}
}
