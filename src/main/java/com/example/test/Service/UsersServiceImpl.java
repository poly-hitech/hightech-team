package com.example.test.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.UsersDao;
import com.example.test.Model.Users;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDao dao;
	
	@Override
	public Boolean login(Users item) {
		Users result = dao.login(item);
		if(result != null) {
			BeanUtils.copyProperties(result, item);
			
			item.setPassword(null);
		
			return true;
		}
		return false;
	}

}
