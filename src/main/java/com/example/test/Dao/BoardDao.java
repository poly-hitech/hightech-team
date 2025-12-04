package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.ForumPost;
import com.example.test.Model.ForumPostFile;
import com.example.test.Model.ForumPostReview;

/**
 * BoardDao 인터페이스는 게시판 관련 데이터베이스 접근 작업을 위한 명세를 정의합니다.
 * 이 인터페이스를 구현하는 클래스(예: BoardDaoImpl)는 실제 DB와의 상호작용 로직을 구현해야 합니다.
 */

public interface BoardDao {

	void addPost(ForumPost forumPost);
	
	ForumPost getpost(Long postId);

	void delPost(Long postId);

	void modiPost(ForumPost forumPost);

	List<ForumPostReview> getComments(Long postId);

	void addComment(ForumPostReview forumPostReview);

	void savePostFile(ForumPostFile postFile);

	void delPostComment(Long postId);

	List<ForumPostFile> getPostFiles(Long postId);

	List<ForumPost> getBoardList(Long boardId);

	List<ForumPost> getBoardListOnlyFive(long boardId);

}
