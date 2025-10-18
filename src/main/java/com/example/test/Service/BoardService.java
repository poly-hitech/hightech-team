package com.example.test.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.test.Model.ForumPostReview;
import com.example.test.Model.ForumPost;

/**
 * BoardService 인터페이스는 게시판 관련 비즈니스 로직을 처리하기 위한 명세를 정의합니다.
 * 이 계층은 Controller의 요청을 받아 DAO를 호출하고, 데이터 가공 및 트랜잭션 처리 등의 핵심 로직을 수행합니다.
 */

public interface BoardService {

	//반환할 거 있음
	List<ForumPost> getboard(Long boardId);

	//반환 할 거 없음
	void addPost(ForumPost forumPost, Long boardId, HttpSession session);

	ForumPost getpost(Long postId);

	void delPost(Long postId);

	void modiPost(ForumPost forumPost, Long postId, HttpSession session);

	ForumPost getPostDetail(Long postId);

	List<ForumPostReview> getComments(Long postId);

	void addComment(ForumPostReview forumPostReview, Long postId, HttpSession session);

	
}
