package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.NewRegex;
import com.example.test.Model.RegexDetail;
import com.example.test.Model.Users;

public interface UsersDao {

	Users login(Users item);

	void add(Users item);

	Long getLastRegexId();

	List<RegexDetail> selectRegexDetail(String username);

	Long getLastNewRegexId();

	void saveRegexDetail(List<RegexDetail> list);

	void saveNewRegex(List<NewRegex> list2);

	Long selectUserId();

	List<NewRegex> selectNewRegex(String username);

	Long getFirstNewRegexId(String username);

}
