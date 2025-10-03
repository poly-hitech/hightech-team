package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.ForumPost;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession sql;
	
	
	@Override
	public List<ForumPost> getBoard(Long boardId) {
		// TODO Auto-generated method stub
		return sql.selectList("board.getBoard", boardId);
	}

}
