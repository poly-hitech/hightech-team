package com.example.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.BoardDao;
import com.example.test.Model.ForumPost;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;

	@Override
	public List<ForumPost> getboard(Long boardId) {
		// TODO Auto-generated method stub
		return boardDao.getBoard(boardId);
	}

}
