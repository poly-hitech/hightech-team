package com.example.test.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Override
	public void add(Users item) {
		String[] stringPass = {"~","!","@","#","$","%","&","*"};
		String[] passEncode = new String[0];
		String regex = "[!@#$%^&*]";
		
		//사용자가 입력한 비밀 번호 가져오기
		String password = item.getPassword();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		//사용자가 입력한 비밀번호의 문자열을 문자로 분해하기
		for(int a =1 ; a <= password.length(); a++) {
			//문자열의 위치의 값 찾아서 변수 지정
			String charpass = password.substring(a-1,a);
			//배열의 홀수 위치에 분리된 비밀번호 저장하기
			passEncode[a*2-1] = charpass;
			
			//특수문자가 포함되어 있다면
			if(matcher.find()){
				//생성되어 있는 마지막 번호 호출
				Long regexId = dao.getLastRegexId();
				
				//사용자가 입력한 패스워드 내의 특수문자의 총 갯수 확인
			
				//특수문자의 갯수만큼 반복
				for() {
					
				}
				
				//특수문자와 특수 문자 위치 저장
				item.getRegexDetail().se(charpass);
				item.setRegexLocation(a*2-1);
				}
		}
		
		//passEncode의 짝수위치에 특수문자 랜덤 추가
		for(int a = 0 ; a < password.length() ; a++) {
			int random = (int)(Math.random()*7);
			
			passEncode[a*2] = stringPass[random]; 
		}
		//배열 속 문자들을 문자열로 변환
		String newpassword = String.valueOf(passEncode);
		
		//변환된 문자열을 패스워드로 새로 저장
		item.setPassword(newpassword);
		
		dao.add(item);
	}

}
