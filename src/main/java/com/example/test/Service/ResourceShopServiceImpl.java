package com.example.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Dao.ResourceDao;
import com.example.test.Model.Market;
import com.example.test.Model.ResourceShop;
import com.example.test.Util.FileUploadUtil;

@Service
public class ResourceShopServiceImpl implements ResourceShopService {
	
	@Autowired
	ResourceDao dao;

	@Autowired
	FileUploadUtil fileupload;
	
	@Override
	public List<Market> list() {
		
		return dao.list();
	}

	@Override
	public List<Market> list(Long userId) {
		// TODO Auto-generated method stub
		return dao.list(userId);
	}

	@Override
	public void addResource(ResourceShop resource, MultipartFile file, Model model) throws Exception{
		
		String filepath = fileupload.saveFile(file, model);
		
		if (filepath != null && !filepath.isEmpty()) {
            // 파일이 정상적으로 업로드된 경우에만 경로를 설정
            resource.setResourceFile(filepath);
            dao.save(resource);
        } else {
            model.addAttribute("message", "파일 업로드에 실패하였습니다.");
        }
		
	}}
