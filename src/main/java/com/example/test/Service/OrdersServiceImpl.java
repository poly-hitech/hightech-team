package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.test.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.Dao.CountingDao;
import com.example.test.Dao.OrdersDao;
import com.example.test.Dao.RankingDao;
import com.example.test.Dao.ResourceDao;
import com.example.test.Dao.UsersDao;

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
	
	@Autowired
	RankingDao rankingDao;

	// 주문하기(차후에 시간되면 장바구니 구현할것 고려해서 모듈화 고려해서 작성)
	@Transactional
	@Override
	public void buyResource(Long userId, Long itemId, List<ResourceShop> shop) {
		// 주문아이템이 있는지 체킹
		if (shop == null || shop.isEmpty()) {
			log.info("주문 항목이 비어있습니다.", shop);
			throw new IllegalArgumentException("주문 항목이 비어있습니다.");
		}

		// 로그인 한 회원을 조회함.
		Users user = userDao.item(userId);

		// 주문 생성
		Orders orders = new Orders();

		// 주문 정보
		// 주문자 정보 설정
		orders.setUserId(userId);
		String nickName = user.getNickname();
		// 주문자 닉네임 설정
		orders.setOrdersUser(nickName);

		// 주문상세 호출
		List<OrdersDetails> ordersDetailsList = new ArrayList<>();

		// 주문 상세 및 총 금액 계산(다중 및 단일 처리)
		for (ResourceShop singleShop : shop) {
			// 각 리소스별 아이템번호
			itemId = singleShop.getItemId();
			OrdersDetails ordersDetails = new OrdersDetails();
			// 아이템 번호(장바구니를 통해서 주문이 들어올 경우도 상정)
			ordersDetails.setItemId(itemId);
			// 수량 호출(바로 구매 일 경우 그냥 하나만 구매하는 걸로 가정함)
			int amount = ordersDetails.getAmount() > 0 ? ordersDetails.getAmount() : 1;
			
			// 해당 아이템 주문의 주문 금액 계산
			Long itemPrice = singleShop.getItemPrice() * amount;
			ordersDetails.setDetailitemPrice(itemPrice);
			// 총 주문 금액 설정
			if (orders.getOrdersPrice() == null) orders.setOrdersPrice(0L);	//호출한 값이 없을수도 있음으로 초기값 설정
			orders.setOrdersPrice(orders.getOrdersPrice() + itemPrice);
			// 주문상세 리스트에 주문상세내용 추가
			ordersDetailsList.add(ordersDetails);

			// 주문 성공시 아이템 가격만큼 주문자의 포인트 차감 & 판매자의 포인트 증가
			// 주문자 정보를 기준으로 포인트 차감
			BuyPoint orderUserPoint = userDao.getPointByUserId(userId);
			// 주문자의 포인트가 주문 금액보다 적으면 예외 처리
			if (orderUserPoint.getPointMoney() < itemPrice) {
				log.error("주문자의 포인트가 부족합니다. 현재 포인트: {}, 주문 금액: {}", orderUserPoint, itemPrice);
				throw new IllegalArgumentException("주문자의 포인트가 부족합니다.");
			}
			// 주문자의 포인트 차감
			orderUserPoint.setPointMoney(orderUserPoint.getPointMoney() - itemPrice);
			log.info("주문자의 포인트가 차감되었는지 확인: {}" , orderUserPoint.getPointMoney());
			userDao.disPointByUserId(orderUserPoint);

			// 판매자 정보를 기준으로 포인트 증가
			BuyPoint saleUserPoint = userDao.getPointByNickname(singleShop.getItemWriter());
			saleUserPoint.setPointMoney(saleUserPoint.getPointMoney() + itemPrice);
			log.info("판매자의 포인트가 증가되었는지 확인: {}" , saleUserPoint.getPointMoney());
			// 닉네임 기반으로 가져온 포인트 객체를 전달해서 다시 해당 정보안에 있는 유저번호와 함께 객체를 전달하여 수정
			userDao.earnPointByUserId(saleUserPoint);

			//CQRS 혹은 캐시(대용량 트래픽 처리에 있어서 캐시로 저장해뒀다가 한번에 처리 하는 것이 용이함.)
			//(쓰기, 변경, 삭제는 자주하는 것이 대용량 트래픽 발생으로 이어지기 때문에 좋지않은 방식임.)
			//구조적으로 좋은 설계가 아니기에 캐실 처리 필요.
			// 주문 성공시 해당 아이템의 판매량 증가
			// 해당 아이덴에 해당하는 counting 테이블을 가져옴
			Counting counting = countingDao.searchByItemId(singleShop.getItemId());
			// 해당 아이템의 전체 판매량 증가
			counting.setTotalcount(counting.getTotalcount() + amount);
			// 해당 아이템의 일일 판매량 증가
			counting.setDailycount(counting.getDailycount() + amount);
			// 해당 아이템의 주간 판매량 증가
			counting.setWeeklycount(counting.getWeeklycount() + amount);
			// 해당 아이템의 월간 판매량 증가
			counting.setMonthlycount(counting.getMonthlycount() + amount);
			// countingDao를 통해서 해당 아이템의 판매량 변경
			countingDao.countingUpdateByItemId(counting);
			
			//count의 값들이 증가하면 이에 따라서 랭킹도 새로 설정
//			rankingDao.update(singleShop.getItemId());
			

		}

		// 주문 넣기(주문 정보 저장) 저장된 주문의 주문 번호를 가지고 옴
		Long ordersId = ordersDao.buyResource(orders);
		
		log.info("주분번호: {}", ordersId);

		// 주문 상세 저장(리스트로 반복돌리면서 주문상세정보를 저장함)
		for (OrdersDetails ordersDetails : ordersDetailsList) {
			ordersDetails.setOrdersId(ordersId);
			log.info("주문 상세 아이템번호 세팅 확인: {}", ordersDetails.getItemId(),"주문상세 주문번호 세팅 확인: {}", ordersDetails.getOrdersId());
			ordersDao.saveOrdersDetails(ordersDetails);
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MyOrderList> purchasedResources(Long userId) {
		return ordersDao.purchasedResources(userId);
	}

	@Override
	public List<Long> getItemIdByLoginUser(Long userId) {
		return ordersDao.getItemIdByLoginUser(userId);
	}

}
