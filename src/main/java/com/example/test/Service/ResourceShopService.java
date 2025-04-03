package com.example.test.Service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Model.Market;
import com.example.test.Model.ResourceShop;
import com.example.test.Util.FileUploadUtil;

public interface ResourceShopService {

	List<Market> list();

	List<Market> list(Long userId);

	void addResource(ResourceShop resource, MultipartFile file, Model model) throws Exception;

}
