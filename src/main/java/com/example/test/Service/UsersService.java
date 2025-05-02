package com.example.test.Service;

import com.example.test.Model.Users;

public interface UsersService {
	Boolean login(Users item);

	void add(Users item);

	Users item(Long userId);

	void update(Users item);

	void delete(Long userId);
}
