package com.example.test.Dao;

import java.util.List;
import java.util.Map;

import com.example.test.Model.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<MyOrderList> purchasedResources(Long userId) {
		return sql.selectList("orders.purchasedList", userId);
	}

	//로그인 한 사용자가 구매한 리소스 번호
	@Override
	public List<Long> getItemIdByLoginUser(Long userId) {
		return sql.selectList("orders.getItemIdByLoginUser", userId);
	}

	// 사용자 ID와 아이템 ID로 주문 상세 정보를 조회
	@Override
	public OrdersDetails getOrdersDetailsByUserIdAndItemId(Map<String, Object> params) {
		return sql.selectOne("ordersDetails.getOrdersDetailsByUserIdAndItemId", params);
	}
}
