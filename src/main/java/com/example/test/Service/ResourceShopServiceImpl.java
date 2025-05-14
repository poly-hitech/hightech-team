package com.example.test.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Dao.ResourceDao;
import com.example.test.Model.Market;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
import com.example.test.Model.Users;
import com.example.test.Util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResourceShopServiceImpl implements ResourceShopService {
	
	@Autowired
	ResourceDao dao;

	@Autowired
	FileUploadUtil fileupload;
	
	@Override
	public List<ResourceCategory> list() {
		
		return dao.list();
	}

	@Override
	public List<ResourceCategory> list(Long userId) {
		// TODO Auto-generated method stub
		return dao.list(userId);
	}
	
	@Transactional
	@Override
	public List<ResourceCategory> addResourcePage() {
		return dao.addResourceShop();
	}

	@Transactional
	@Override
	public void addResource(Long userId, Market market, List<MultipartFile> file, Model model) throws Exception{
		
		//resourceFile 리스트 초기화
		List<ResourceFile> rf = new ArrayList<>();
		
		// 파일 개수와 ResourceFile 리스트 개수 확인
	    if (file == null || file.isEmpty()) {
	    	log.info("업로드된 파일이 없습니다.");
	        model.addAttribute("message", "업로드된 파일이 없습니다.");
	        return;
	    }
		//파일 업로드(업로드와 경로 추출)
		for(int loop = 0; loop < file.size() ;loop++) {
	        MultipartFile singlefile = file.get(loop);
			String filepath = fileupload.saveFile(singlefile, model);
			
            // 파일이 정상적으로 업로드된 경우에만 경로를 설정		
			if (filepath != null && !filepath.isEmpty()) {
				//반환된 파일 저장 경로를 가져와 저장
				ResourceFile resourcefile = new ResourceFile();
				resourcefile.setResourceFileName(filepath);
				rf.add(resourcefile);
				log.info("리소스파일에 파일명이 들어갔는지 확인하기"+resourcefile.getResourceFileName());
	            
	        } else {
	        	log.info("파일 업로드에 실패했습니다.");
	            model.addAttribute("message", "파일 업로드에 실패하였습니다.");
	            return;
	        }
		}
		market.getResourceShop().setUserId(userId);
		market.getResourceShop().setResourceFile(rf);	//리소스 파일 리스트를 리소스마켓에 저장
		

		dao.save(market.getResourceShop());	//리소스 정보 업로드
        
        //리소스가 업로드 되지않았을 경우 예외 발생
        if(market.getResourceShop() == null) {
        	log.info("생성된 리소스 정보가 없습니다.");
        	model.addAttribute("message", "생성된 리소스 정보가 없습니다.");
        } else {
        	log.info("모든 파일 업로드 및 리소스 등록이 완료되었습니다.");
            model.addAttribute("message", "모든 파일 업로드 및 리소스 등록이 완료되었습니다.");
        }
		
	}

}
