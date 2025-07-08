package com.example.test.Service;

import com.example.test.Model.Users;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface UsersService {
	Boolean login(Users item);

	void add(Users item);

	Users item(Long userId);

	void update(Users item, MultipartFile profileImage, Model model) throws Exception;

	void delete(Long userId);
}
