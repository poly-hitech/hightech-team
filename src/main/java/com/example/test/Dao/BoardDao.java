package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.ForumPost;

public interface BoardDao {

	List<ForumPost> getBoard(Long boardId);

}
