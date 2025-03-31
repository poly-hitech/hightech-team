package com.example.test.Util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	@Autowired
	FileUploadPathResolver pathModuler = new FileUploadPathResolver();
	
	
	public String saveFile(MultipartFile file, Model model) throws Exception{
		
		if (file.isEmpty()) {
            model.addAttribute("message", "파일을 선택해주세요!");
            return "uploadResult";
        }

        // 업로드 경로 설정
        String uploadDir = pathModuler.getUploadPath(file.getOriginalFilename());
        File uploadFolder = new File(uploadDir);

        // 디렉토리가 존재하지 않으면 생성
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        try {
            // 파일 저장
            File destinationFile = new File(uploadDir + "/" + file.getOriginalFilename());
            file.transferTo(destinationFile);

            model.addAttribute("message", "파일 업로드 성공!");
            model.addAttribute("filePath", destinationFile.getAbsolutePath());

        } catch (Exception e) {
            model.addAttribute("message", "파일 업로드 중 오류 발생: " + e.getMessage());
        }
		return uploadDir;
	}
}
