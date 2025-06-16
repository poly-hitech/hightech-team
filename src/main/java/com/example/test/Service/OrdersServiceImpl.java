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
	
	//주문하기(차후에 시간되면 장바구니 구현할것 고려해서 모듈화 고려해서 작성)
	@Transactional
	@Override
	public void buyResource(Long userId, Long itemId, List<ResourceShop> shop) {
		//주문아이템이 있는지 체킹
		if(shop == null || shop.isEmpty()) {
			log.info("주문 항목이 비어있습니다." , shop);
		}
		
		//로그인 한 회원을 조회함.
		Users user = userDao.item(userId);
		
		//주문 생성
		Orders orders = new Orders();
		
		//주문 정보
		//주문자 정보 설정
		orders.setUserId(userId);
		String nickName = user.getNickname();
		//주문자 닉네임 설정
		orders.setOrdersUser(nickName);

		//주문상세 호출
		List<OrdersDetails> ordersDetailsList = new ArrayList<>();
		
		//주문 상세 및 총 금액 계산(다중 및 단일 처리)
		for(ResourceShop singleShop: shop) {
			//각 리소스별 아이템번호
			itemId = singleShop.getItemId();
			OrdersDetails ordersDetails = new OrdersDetails();
			//아이템 번호(장바구니를 통해서 주문이 들어올 경우도 상정)
			ordersDetails.setItemId(itemId);
			//수량 호출
			Long amount= ordersDetails.getAmount();
			//해당 아이템 주문의 주문 금액 계산
			Long itemPrice = singleShop.getItemPrice() * amount;
			//총 주문 금액 설정
			orders.setOrdersPrice(orders.getOrdersPrice() + itemPrice);
			//주문상세 리스트에 주문상세내용 추가
			ordersDetailsList.add(ordersDetails);
			
			//주문 성공시 아이템 가격만큼 주문자의 포인트 차감 & 판매자의 포인트 증가
			userDao.disPoint(userId);
			//판매자 정보를 기준으로 포인트 변경
			userDao.earnPoint(singleShop.getItemWriter());
			
		}
		
		//주문 넣기(주문 정보 저장)
		ordersDao.buyResource(orders);
		
		//주문 상세 저장(리스트로 반복돌리면서 주문상세정보를 저장함)
		for(OrdersDetails ordersDetails : ordersDetailsList) {
			ordersDao.saveOrdersDetails(ordersDetailsList);
			//주문이 성공하면 판매량 증가
			countingDao.buyCountingUp(ordersDetails.getOrdersDetailsId());
		}

	}

}
