package com.example.test.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.test.Service.CountingService;
import com.example.test.Service.RanksService;


// 스케쥴러를 사용한 일간/주간/월간 초기화
@Component
public class Counting {
	
    @Autowired
    CountingService CountingService;
    
    @Autowired
    RanksService RankService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyCount() {
    	CountingService.resetDailyCount();
    	
    	RankService.resetDailyRank();
    }

	// 주간 조회수 초기화 (매주 월요일 자정에 실행)
    @Scheduled(cron = "0 0 0 * * MON")
    public void resetWeekCount() {
    	CountingService.resetWeeklyCount();
    	
    	RankService.resetWeeklyRank();
    }
    
    // 월간 조회수 초기화 (매월 1일 자정에 실행)
    @Scheduled(cron = "0 0 0 1 * *")
    public void resetMonthCount() {
    	CountingService.resetMonthlyCount();
    	
    	RankService.resetMonthlyRank();
    }
}
