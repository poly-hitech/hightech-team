package com.example.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Dao.ResourceDao;
import com.example.test.Model.Market;
import com.example.test.Model.MarketList;
import com.example.test.Model.Users;
import com.example.test.Util.FileUploadUtil;

@Service
public class ResourceShopServiceImpl implements ResourceShopService {
	
	@Autowired
	ResourceDao dao;

	@Autowired
	FileUploadUtil fileupload;
	
	@Override
	public List<MarketList> list() {
		
		return dao.list();
	}

	@Override
	public List<MarketList> list(Long userId) {
		// TODO Auto-generated method stub
		return dao.list(userId);
	}

	@Transactional
	@Override
	public void addResource(Long userId, Market market, MultipartFile file, Model model) throws Exception{
		
		//파일 업로드(업로드와 경로 추출)
		String filepath = fileupload.saveFile(file, model);		//반환된 파일 업로드 경로를 가져와 변수저장
		
		if (filepath != null && !filepath.isEmpty()) {
            // 파일이 정상적으로 업로드된 경우에만 경로를 설정
			market.getResourceShop().setResourceFile(filepath);		//파일 경로를 db에 저장하기 위함
			
			market.getResourceShop().setUserId(userId);
            market.getResourceShop().setResourceSubCategoryId(market.getResourceSubCategory().getResourceSubCategoryId());
            dao.save(market.getResourceShop());	//리소스 정보 업로드
            
            //리소스가 업로드 되지않았을 경우 예외 발생
            if(market.getResourceShop() == null) {
            	model.addAttribute("message", "생성된 리소스 정보가 없습니다.");
            }
                        
        } else {
            model.addAttribute("message", "파일 업로드에 실패하였습니다.");
        }
		
	}

}
