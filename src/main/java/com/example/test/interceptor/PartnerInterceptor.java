package com.example.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.test.Model.Users;
import com.example.test.Model.UsersRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PartnerInterceptor implements HandlerInterceptor  {
	
	@SuppressWarnings("null")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Users member = (Users) session.getAttribute("member");
		
		if(member != null) {
			//로그인한 파트너 사용자 또는 관리자
			if(UsersRole.PARTNER.equals(member.getRoleId()) ||  UsersRole.ADMIN.equals(member.getRoleId())) {

				return true;
			}
			log.info("session에 담긴 값 확인 : ", member.getRoleId());
			
			response.sendRedirect("/");
			return false;
		}
		//로그인 하지 않은 사람
		response.sendRedirect("/login");
		return false;
	}

}