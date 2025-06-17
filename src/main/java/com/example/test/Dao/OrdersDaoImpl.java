package com.example.test.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	SqlSession sql;
	
	//주문정보를 저장하고 주문번호를 리턴 받음
	@Override
	public Long buyResource(Orders orders) {
		return (long) sql.insert("orders.buyResource", orders);
	}

	@Override
	public void saveOrdersDetails(OrdersDetails ordersDetails) {
		sql.insert("ordersDetails.saveOrdersDetails", ordersDetails);
	}

}
