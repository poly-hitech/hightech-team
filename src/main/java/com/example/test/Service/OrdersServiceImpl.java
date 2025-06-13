package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.Dao.OrdersDao;
import com.example.test.Dao.ResourceDao;
import com.example.test.Dao.UsersDao;
import com.example.test.Model.Counting;
import com.example.test.Model.Orders;
import com.example.test.Model.OrdersDetails;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDao ordersDao;
	
	@Autowired
	ResourceDao shopDao;
	
	@Autowired
	UsersDao userDao;
	
	@Autowired
	CountingDao countingDao;
	
	@Transactional
	@Override
	public void buyResource(Long userId, Long itemId, Orders orders, ResourceShop shop) {
		//로그인 한 회원을 조회함.
		Users user = userDao.item(userId);
		
		//주문자 정보 설정
		orders.setUserId(userId);
		String nickName = user.getNickname();
		//주문자 닉네임 설정
		orders.setOrdersUser(nickName);
		//새로운 주문 상세 생성
		OrdersDetails ordersDetails;
		Long Amount = ordersDetails.getAmount();
		List<OrdersDetails> ordersDetailList = new ArrayList<>();
		for( : ordersDetailList) {
			//주문 번호는 시퀀스로 자동 생성 예정
			//주문 아이템번호
			ordersDetails.setItemId(itemId);
			//주문번호는 키값 자동주입
			ordersDetailList.add(ordersDetails);
		};

		//주문 넣기(주문 정보, 주문 상세 insert)
		ordersDao.buyResource(orders);
		
		//주문 성공시 아이템 가격만큼 주문자의 포인트 차감 & 판매자의 포인트 증가
		userDao.disPoint(userId);
		//판매자 정보를 기준으로 포인트 변경
		userDao.earnPoint(shop.getItemWriter());
		//주문이 성공하면 
		
		//주문이 성공하면 판매량 증가
		countingDao.buyCountingUp(ordersDetailsId);
	}

}
