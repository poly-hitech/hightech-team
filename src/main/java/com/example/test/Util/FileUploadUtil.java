package com.example.test.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUploadUtil {

	@Autowired
	FileUploadPathResolver pathModule;
	
	
	public String saveFile(MultipartFile file, Model model) throws Exception{
		
			if (file==null||file.isEmpty()) {
	            model.addAttribute("message", "파일을 선택해주세요!");
	            return null;
	        }
			//고유값 생성
			String uuid = UUID.randomUUID().toString();
	        // 업로드 경로 설정
	        String uploadDir = pathModule.getUploadPath(file.getOriginalFilename());	//확장자를 구분하는 메소드 호출
	        File uploadFolder = new File(uploadDir);
	
	        // 디렉토리가 존재하지 않으면 생성
	        if (!uploadFolder.exists()) {
	            uploadFolder.mkdirs();
	        }
	
	        try {
	            // 파일 저장
	            File destinationFile = new File(uploadDir + "/" + uuid +"_"+ file.getOriginalFilename());
	            file.transferTo(destinationFile);
	
	            model.addAttribute("filePath", destinationFile.getAbsolutePath());
	            log.info("저장경로 확인: {}" + uploadDir);
	            //저장 경로 반환
	            String filepath = destinationFile.getAbsolutePath();	//상세 정보 필요
	            filepath = filepath.replace("\\", "/"); // 백슬래시를 슬래시로 변환
	    		return filepath;//서비스로 반환된 것을 저장 필요
	
	        } catch (Exception e) {
	            model.addAttribute("message", "파일 업로드 중 오류 발생: " + e.getMessage());
	            return null;
	        }

	}	
}
