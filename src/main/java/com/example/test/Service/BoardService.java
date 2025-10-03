package com.example.test.Service;

import java.util.List;

import com.example.test.Model.ForumPost;

public interface BoardService {

	List<ForumPost> getboard(Long boardId);

}
