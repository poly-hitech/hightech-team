package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.ForumPost;
import com.example.test.Model.ForumPostReview;



@Repository
public class BoardDaoImpl implements BoardDao {

	// MyBatis와 DB 연결 및 SQL 실행을 담당하는 핵심 객체인 SqlSession을 Spring이 주입
	@Autowired
	SqlSession sql;
	
	
	@Override
	public List<ForumPost> getBoard(Long boardId) {
		// TODO Auto-generated method stub
		return sql.selectList("board.getBoard", boardId);
		/* 형식 : sql.태그타입("매퍼파일의 namespace값, 매퍼파일의 id값", 인자값); */
	}

	@Override
	public void addPost(ForumPost forumPost) {
		// TODO Auto-generated method stub
		sql.insert("board.addPost", forumPost);
	}
	
	@Override
	public ForumPost getpost(Long postId) {
		return sql.selectOne("board.getpost", postId);
	}
	
	@Override
	public void delPost(ForumPost forumPost) {
		sql.delete("board.delPost", forumPost);
	}
	
	@Override
	public void modiPost(ForumPost forumPost) {
		sql.update("board.modiPost", forumPost);
	}
	
	@Override
	public List<ForumPostReview> getComments(Long postId) {
		return sql.selectList("board.getComments", postId);
	}
	
	@Override
	public void addComment(ForumPostReview forumPostReview) {
		sql.insert("board.addComment", forumPostReview);
	}

}
