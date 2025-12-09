package com.example.test.Service;

import com.example.test.Model.ResourceShop;
import java.util.List;

public interface RanksService {

	void resetDailyRank();

	void resetWeeklyRank();

	void resetMonthlyRank();

	List<ResourceShop> getTotalTop(int limit);
	List<ResourceShop> getdailyTop(int limit);
	List<ResourceShop> getweeklyTop(int limit);
	List<ResourceShop> getmonthlyTop(int limit);

}
