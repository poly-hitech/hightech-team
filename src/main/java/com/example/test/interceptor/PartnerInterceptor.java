package com.example.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.test.Model.Users;
import com.example.test.Model.UsersRole;

@Component
public class PartnerInterceptor implements HandlerInterceptor  {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Users member = (Users) session.getAttribute("member");
		
		if(member != null) {
			//로그인한 일반 사용자
			if(UsersRole.NORMAL.equals(member.getRoleId())) {

				return true;
			}
				
			response.sendRedirect("/");
			return false;
		}
		//로그인 하지 않은 사람
		response.sendRedirect("/login");
		return false;
	}

}