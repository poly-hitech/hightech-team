package com.example.test.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ForumPostReview {
	private long commentId; 
    private String commentContent;  
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date commentWriteDate = new Date();
    private String commentWriter;   
    private long postId;            
    private long userId;
    
    public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommnetContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentWriteDate() {
		return commentWriteDate;
	}
	public void setCommentWriteDate(Date commentWriteDate) {
		this.commentWriteDate = commentWriteDate;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
