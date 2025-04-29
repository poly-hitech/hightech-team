package com.example.test.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.Dao.UsersDao;
import com.example.test.Model.NewRegex;
import com.example.test.Model.RegexDetail;
import com.example.test.Model.ResourceSubCategory;
import com.example.test.Model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDao userdao;
	
	//로그인 암호화1번 방법
	//저장된 특수문자와 경로를 모두 불러와서 사용자가 입력한 패스워드에 합쳐서 비교하도록함
	@Transactional
	@Override
	public Boolean login(Users item) {
		//사용자가 입력한 비밀번호 호출
		String password = item.getPassword();
		//전체 비밀번호가 저장될 배열
		String[] passEncode = new String[password.length()*2];
		log.info("사용자가 입력한 비밀번호: " + password);
		log.info("사용자가 입력한 비밀번호의 길이: " + password.length());
		
		for(int a = 1 ; a <= password.length(); a++) {
			//문자열의 위치의 값 찾아서 변수 지정
			String charpass = password.substring(a-1,a);
			//배열의 홀수 위치에 분리된 비밀번호 저장하기
			passEncode[a*2-1] = charpass;
			log.info("문자열이 분해된 문자: " + charpass);
		}
		
		//유저 아이디를 기준으로 특수문자 조회
		List<NewRegex> newRegex = userdao.selectNewRegex(item.getUsername());
		//조회된 특수문자들의 길이 확인
		int countRegex = newRegex.size();
		//사용자에 해당하는 특수문자의 첫번째 순번 호출
		int newRegexId = userdao.getFirstNewRegexId(item.getUsername()).intValue();
		log.info("특수문자 순번 호출" + newRegexId);
		
			//조회된 특수문자의 길이만큼 반복
			for(int a =1; a<=countRegex ;a++) {
				NewRegex newregex = newRegex.get(newRegexId);	//리스트로 받아온 임의의 특수문자 정보를 단일 객체로 변환
				newRegexId++;
				//특수문자가 배열에 들어갈 위치
				Long newRegexLocation = newregex.getNewRegexLocation();
				int newregexLocation = newRegexLocation.intValue();
				//배열에 들어갈 특수문자
				String newRegexInfo = newregex.getNewRegexInfo();
				//passEncode의 특정위치에 특수문자 삽입
				passEncode[newregexLocation] = newRegexInfo;
			}
		//패스워드 배열을 문자열로 변환
		String newpassword = String.valueOf(passEncode);
		//변환된 패스워드 문자열 저장
		item.setPassword(newpassword);
		//사용자가 입력한 아이디와 암호화 된 패스워드를 데이터베이스에서 비교
		Users result = userdao.login(item);
		if(result != null) {
			BeanUtils.copyProperties(result, item);
			
			item.setPassword(null);
		
			return true;
		}
		return false;
	}

	//패스워드 인코더없이 비밀번호 암호화 구현 시도.
	//회원가입
	@Transactional
	@Override
	public void add(Users item) {
		//저장된 유저정보 중 최신의 userId 호출
		Long userId = userdao.selectUserId();
		if(userId == null) {
			userId = 0L;
		}
		userId++;
		item.setUserId(userId);
		log.info("사용자 순번 확인: " + userId);
		//사용자가 입력한 비밀 번호 가져오기
		String password = item.getPassword();
		log.info("사용자가 입력한 비밀번호: " + password);
		String[] stringPass = {"~","!","@","#","$","%","&","*"};
		String[] passEncode = new String[password.length()*2];
		String regex = "[~!@#$%^&*]";
		
		//특수문자 확인
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		//사용자가 입력한 특수문자 리스트
		List<RegexDetail> list = item.getRegexDetail();
		
		//임의로 지정할 특수문자 리스트
		List<NewRegex> list2 = item.getNewRegex();

		//사용자가 입력한 비밀번호의 문자열을 문자로 분해하기
		for(int a =1 ; a <= password.length(); a++) {
			//문자열의 위치의 값 찾아서 변수 지정
			String charpass = password.substring(a-1,a);
			//배열의 홀수 위치에 분리된 비밀번호 저장하기
			passEncode[a*2-1] = charpass;
			
			//특수문자가 포함되어 있다면
			if(matcher.find()){
				//생성되어 있는 마지막 번호 호출
				Long regexId = userdao.getLastRegexId();
				
				//사용자가 입력한 특수문자와 특수 문자 위치 저장
				RegexDetail regexDetail = new RegexDetail();
				regexId++;
				regexDetail.setRegexId(regexId);
				regexDetail.setRegexInfo(charpass);
				regexDetail.setRegexLocation((long) (a*2-1));
				regexDetail.setUserId(userId);
				
				list.add(regexDetail);
			}
		}
		userdao.saveRegexDetail(list);
		
		//passEncode의 짝수위치에 특수문자 랜덤 추가
		for(int a = 0 ; a < password.length() ; a++) {	//사용자가 입력한 패스워드의 길이에 맞춰서 그 길이 만큼 특수문자 추가
			int random = (int)(Math.random()*7);
			
			//0부터 시작하여 그 이후엔 2의 배수의 위치에 특수문자 랜덤 저장
			String charpass = stringPass[random];
			log.info("임의 생성된 특수문자 확인: " + charpass);
			passEncode[a*2] = charpass;
			
			//생성된 임의의 특수문자 중 최신 번호 호출
			Long newRegexId = userdao.getLastNewRegexId();
			log.info("생성된 임의의 특수문자 최신 번호 확인: " + newRegexId);
			
			//임의의 특수문자 생성, 특수문자의 위치 저장
			NewRegex newRegex = new NewRegex();
			newRegexId++;
			newRegex.setNewRegexId(newRegexId);
			newRegex.setNewRegexInfo(charpass);
			newRegex.setNewRegexLocation((long) (a*2));
			newRegex.setUserId(userId);
			
			list2.add(newRegex);		
		}
		userdao.saveNewRegex(list2);
		
		//배열 속 문자들을 문자열로 변환
		String newpassword = String.valueOf(passEncode);
		log.info("저장될 비밀번호 확인: " + newpassword);
		
		//변환된 문자열을 패스워드로 새로 저장
		item.setPassword(newpassword);
		item.setRoleId(1L);
		
		userdao.add(item);
	}

}
