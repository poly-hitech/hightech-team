package com.example.test.Service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Dao.BoardDao;
import com.example.test.Model.ForumPost;
import com.example.test.Model.ForumPostFile;
import com.example.test.Model.ForumPostReview;
import com.example.test.Model.Users;
import com.example.test.Util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
    FileUploadUtil fileupload;

	@Override
	public List<ForumPost> getboard(Long boardId) {
		// TODO Auto-generated method stub
		// DAO의 getBoard 메서드를 호출하여 DB에서 게시글 목록을 조회하고 결과를 Controller로 반환
		return boardDao.getBoard(boardId);
	}

	@Transactional
	@Override
	public void addPost(ForumPost forumPost, Long boardId, HttpSession session, 
	                    List<MultipartFile> file, Model model) throws Exception {
	    
	    // 1. 세션에서 사용자 정보 추출 및 유효성 검사 ( NPE 방지를 위해 최우선으로 처리)
	    Users users = (Users) session.getAttribute("member");
	    
	    // users가 null인지 확인 (로그인되지 않은 경우)
	    if (users == null) {
	        log.error("사용자 세션 'member'가 null입니다. 로그인되지 않은 사용자 요청.");
	        // View에서 처리할 메시지를 Model에 담거나, RuntimeException을 던져야 함.
	        model.addAttribute("message", "로그인 후 게시글을 작성할 수 있습니다.");
	        return; // 메서드 실행 중단
	    }
	    
	    // 2. 게시글 기본 정보 설정 (users 객체가 유효함을 확인 후 실행)
	    forumPost.setBoardId(boardId);
	    forumPost.setPostWriter(users.getNickname());
	    forumPost.setUserId(users.getUserId());

	    log.info("게시글 정보 - 제목: {} / 작성자: {}", forumPost.getTitle(), users.getNickname());

	    // 3. 게시글 DB 저장 ( Post ID 확보를 위해 먼저 실행)
	    boardDao.addPost(forumPost); // 이 호출 후 forumPost 객체에 postId가 채워짐.
	    
	    // 4. 새로 생성된 게시글 ID 확보 ( 순서를 뒤로 옮겨야 함)
	    Long newPostId = forumPost.getPostId();
	    
	    if (newPostId == null || newPostId == 0) {
	        log.error("게시글 ID 확보 실패. MyBatis <selectKey> 설정 확인 필요.");
	        throw new RuntimeException("게시글 등록 후 ID를 가져오지 못했습니다.");
	    }

	    // 5. 다중 파일 업로드 및 DB 기록
	    if (file != null && !file.isEmpty()) { 
	        for (MultipartFile singleFile : file) {
	            if (singleFile != null && !singleFile.isEmpty()) {
	                
	                String filePath = fileupload.saveFile(singleFile, model); 
	                
	                if (filePath != null) {
	                    ForumPostFile postFile = new ForumPostFile();
	                    postFile.setPostId(newPostId); // 확보된 Post ID 사용
	                    postFile.setFileName(filePath);
	                    
	                    boardDao.savePostFile(postFile); 
	                    log.info("파일 저장 및 DB 기록 완료. 파일 경로: {}", filePath);
	                } else {
	                    // FileUploadUtil에서 오류 메시지가 Model에 담겼을 것
	                    log.warn("파일 업로드 유틸리티가 null을 반환했습니다. 원인: {}", model.getAttribute("message"));
	                }
	            }
	        }
	    } else {
	        log.info("첨부된 파일이 없습니다.");
	    }
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
	
	@Transactional
	@Override
	public void delPost(Long postId) {

		boardDao.delPostComment(postId);
		boardDao.delPost(postId);
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
		forumPostReview.setCommentContent(forumPostReview.getCommentContent());
		
		boardDao.addComment(forumPostReview);
	}
	
	@Override
	public List<ForumPostFile> getPostFiles(Long postId) {
		return boardDao.getPostFiles(postId);
	}

}
