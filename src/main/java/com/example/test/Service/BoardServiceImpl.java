package com.example.test.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.BoardDao;
import com.example.test.Model.ForumPost;
import com.example.test.Model.ForumPostReview;
import com.example.test.Model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;

	@Override
	public List<ForumPost> getboard(Long boardId) {
		// TODO Auto-generated method stub
		// DAO의 getBoard 메서드를 호출하여 DB에서 게시글 목록을 조회하고 결과를 Controller로 반환
		return boardDao.getBoard(boardId);
	}

	@Override
	public void addPost(ForumPost forumPost, Long boardId, HttpSession session) {
		// TODO Auto-generated method stub
		// ForumPost 객체에 게시글이 속할 boardId를 설정
		forumPost.setBoardId(boardId);
				
		// HttpSession에서 "member"라는 이름으로 저장된 로그인 사용자 정보(Users 객체)를 추출
		Users users=(Users)session.getAttribute("member");
				
		// 게시글 객체(forumPost)에 작성자의 닉네임과 사용자 ID를 설정
		// 이로써 DB에 저장될 때 누가 작성했는지 정보가 채워짐 (보안 및 데이터 무결성 확보)
		forumPost.setPostWriter(users.getNickname());
		forumPost.setUserId(users.getUserId());
		log.info("제목 : " + forumPost.getTitle());
		log.info("네용 : " + forumPost.getContent());
		boardDao.addPost(forumPost);
	}
	
	@Override
	public ForumPost getpost(Long postId) {
		// DAO를 호출하여 DB에서 postId에 해당하는 게시글 정보를 조회
		ForumPost forumPost = boardDao.getpost(postId);
		log.info("재목 : " + forumPost.getTitle());
		log.info("네용 : " + forumPost.getContent());
		// 조회된 게시글 객체를 Controller로 반환
		return forumPost;
	}
	
	@Override
	public void delPost(Long postId) {
		ForumPost forumPost = boardDao.getpost(postId);
		boardDao.delPost(forumPost);
	}
	
	@Override
	public void modiPost(ForumPost forumPost, Long postId, HttpSession session) {
		forumPost.setBoardId(postId);
		
		// HttpSession에서 "member"라는 이름으로 저장된 로그인 사용자 정보(Users 객체)를 추출
		Users users=(Users)session.getAttribute("member");
				
		// 게시글 객체(forumPost)에 작성자의 닉네임과 사용자 ID를 설정
		// 이로써 DB에 저장될 때 누가 작성했는지 정보가 채워짐 (보안 및 데이터 무결성 확보)
		forumPost.setPostWriter(users.getNickname());
		forumPost.setUserId(users.getUserId());
		boardDao.modiPost(forumPost);
	}
	
	@Override
	public ForumPost getPostDetail(Long postId) {
		ForumPost forumPost = boardDao.getpost(postId);
		return forumPost;
	}
	
	@Override
	public List<ForumPostReview> getComments(Long postId) {
	    // DAO를 호출하여 댓글 목록을 가져옵니다.
	    return boardDao.getComments(postId);
	}
	
	@Override
	public void addComment(ForumPostReview forumPostReview, Long postId, HttpSession session) {
		forumPostReview.setPostId(postId);
		
		Users users=(Users)session.getAttribute("member");
		
		forumPostReview.setUserId(users.getUserId());
		forumPostReview.setCommentWriter(users.getNickname());
		
		boardDao.addComment(forumPostReview);
	}

}
