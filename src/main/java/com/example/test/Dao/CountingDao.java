package com.example.test.Dao;

import com.example.test.Model.Counting;

public interface CountingDao {

	Counting searchByItemId(Long itemId);

	void countingUpdateByItemId(Counting counting);

	void save(Long itemId);
}
