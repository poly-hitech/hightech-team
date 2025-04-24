package com.example.test.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
