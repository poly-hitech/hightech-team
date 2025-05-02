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

	@Override
	public String search(String username) {
		// TODO Auto-generated method stub
		return sql.selectOne("users.getUsername", username);
	}

	//회원정보 불러오기
	@Override
	public Users item(Long userId) {

		return sql.selectOne("users.items", userId);
	}

	//회원정보 변경(비밀번호 미포함)
	@Override
	public void update(Users item) {
		sql.update("users.update", item);
	}

	//회원탈퇴(영구 삭제가 아닌 roleId를 변경하는 형태로 구현)
	@Override
	public void delete(Long userId) {
		sql.update("users.delete", userId);
		
	}

	//회원정보 수정(비밀번호 포함)
	@Override
	public void updateIncludeNewPassword(Users item) {
		sql.update("users.updateIncludeNewPassword", item);
	}


}
