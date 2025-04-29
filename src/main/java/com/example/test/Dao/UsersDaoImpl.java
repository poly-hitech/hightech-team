package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.NewRegex;
import com.example.test.Model.RegexDetail;
import com.example.test.Model.Users;

@Repository
public class UsersDaoImpl implements UsersDao {
	
	@Autowired
	SqlSession sql;

	@Override
	public Users login(Users item) {
		return sql.selectOne("users.login", item);
	}

	@Override
	public void add(Users item) {
		sql.insert("users.add",item);
		
	}

	@Override
	public Long getLastRegexId() {
		
		return sql.selectOne("users.selectLastRegexId");
	}

	@Override
	public void saveRegexDetail(List<RegexDetail> list) {
		sql.insert("users.addRegex",list);
	}
	
	@Override
	public void saveNewRegex(List<NewRegex> list2) {
		sql.insert("users.addNewRegex",list2);
		
	}

	@Override
	public List<RegexDetail> selectRegexDetail(String username) {

		return sql.selectList("users.selectRegexDetail", username);
	}

	@Override
	public Long getLastNewRegexId() {
		// TODO Auto-generated method stub
		return sql.selectOne("users.selectLastNewRegexId");
	}

	@Override
	public Long selectUserId() {
		// TODO Auto-generated method stub
		return sql.selectOne("users.selectUserId");
	}

	@Override
	public List<NewRegex> selectNewRegex(String username) {
		// TODO Auto-generated method stub
		return sql.selectList("users.selectNewRegex", username);
	}

	@Override
	public Long getFirstNewRegexId(String username) {
		// TODO Auto-generated method stub
		return sql.selectOne("users.getFirstNewRegexId", username);
	}


}
