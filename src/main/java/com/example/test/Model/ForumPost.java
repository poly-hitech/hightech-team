package com.example.test.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ForumPost {
	//게시판에 있는 모델들을 만들어라?
	//한 테이블 당 하나의 클래스 만들기
	private long postId;
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date writeDate = new Date();
	//공개여부 
	private int state;
	private int count;
	private long boardId;
	private long userId;
	private String postWriter;
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	public String getPostWriter() {
		return postWriter;
	}
	public void setPostWriter(String postWriter) {
		this.postWriter = postWriter;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	

}
