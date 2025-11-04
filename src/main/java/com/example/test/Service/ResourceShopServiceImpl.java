package com.example.test.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Dao.CountingDao;
import com.example.test.Dao.RankingDao;
import com.example.test.Dao.ResourceDao;
import com.example.test.Model.Market;
import com.example.test.Model.Ranking;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
import com.example.test.Model.ResourceShop;
import com.example.test.Util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResourceShopServiceImpl implements ResourceShopService {

	@Autowired
	ResourceDao dao;

	@Autowired
	CountingDao countingDao;

	@Autowired
	RankingDao rankingDao;

	@Autowired
	FileUploadUtil fileupload;

	@Override
	public List<ResourceCategory> list() {

		return dao.list();
	}

	@Transactional
	@Override
	public List<ResourceCategory> addResourcePage() {
		return dao.addResourceShop();
	}

	@Transactional
	@Override
	public void addResource(Long userId, Market market, List<MultipartFile> file, MultipartFile resourceImage,
			Model model) throws Exception {

		// resourceFile 리스트 초기화
		List<ResourceFile> rf = new ArrayList<>();
		String resourceImagePath = null;

		// 대표이미지 업로드
		if (resourceImage != null && !resourceImage.isEmpty()) {
			resourceImagePath = fileupload.saveFile(resourceImage, model);
			if (resourceImagePath != null && !resourceImagePath.isEmpty()) {
				market.getResourceShop().setResourceImage(resourceImagePath);
				log.info("대표이미지 파일 업로드 성공: {}", resourceImagePath);
			} else {
				log.warn("대표이미지 파일업로드 실패: {}", resourceImage.getOriginalFilename());
			}
		} else {
			log.info("등록된 대표이미지가 없습니다.");
		}

		market.getResourceShop().setUserId(userId); // 연결 유저 번호
		dao.save(market.getResourceShop()); // 리소스 정보 업로드

		// 다중 파일 업로드
		if (file != null && !file.isEmpty()) {
			// 파일 갯수만틈 돌면서 각각의 파일을 업로드
			for (MultipartFile singleFile : file) {
				if (singleFile != null && !singleFile.isEmpty()) {
					String filePath = fileupload.saveFile(singleFile, model);
					if (filePath != null && !filePath.isEmpty()) {
						// 다중 파일업로드 경로 저장 로직
						ResourceFile resourceFile = new ResourceFile();
						resourceFile.setResourceFileName(filePath);
						resourceFile.setItemId(market.getResourceShop().getItemId());
						dao.saveResourceFile(resourceFile);

						log.info("다중 파일 업로드 중 단일 파일 업로드 성공: {}", filePath);
					} else {
						log.warn("다중 파일업로드 실패: {}", singleFile.getOriginalFilename());
					}
				}
			}
			// // 리소스 정보 등록 후 리소스 파일 등록
			// if (!rf.isEmpty()) {
			// for (ResourceFile resourcefile : rf) {
			// Map<String, Object> params = new HashMap<>();
			// params.put("itemId", market.getResourceShop().getItemId());
			// params.put("resourceFileName", filePath);
			// dao.save(params);
			// }
			// }
			log.info("총 {}개의 다중 파일이 업로드되었습니다.", rf.size());
		} else {
			log.info("등록된 다중 파일이 없습니다.");
		}

		countingDao.save(market.getResourceShop().getItemId());
		long totalCount = dao.countAllItems(); // 전체 상품 수 조회

		Ranking ranking = Ranking.builder()
				.itemId(market.getResourceShop().getItemId())
				.dailyRank(totalCount)
				.weeklyRank(totalCount)
				.monthlyRank(totalCount)
				.totalRank(totalCount)
				.build();
		rankingDao.save(ranking);

		// 리소스가 업로드 되지않았을 경우 예외 발생
		if (market.getResourceShop() == null) {
			log.info("생성된 리소스 정보가 없습니다.");
		} else {
			log.info("모든 파일 업로드 및 리소스 등록이 완료되었습니다.");
		}
	}

	@Override
	public ResourceShop getItemById(Long itemId) {
		log.info("itemId: {}", itemId);

		return dao.getItem(itemId);
	}

	@Override
	public List<ResourceCategory> myResources(Long userId) {

		return dao.myResources(userId);
	}

	@Override
	public void updateMyResource(Long itemId, Market market, MultipartFile file, Model model) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ResourceShop> getTopFromResource() {
		// TODO Auto-generated method stub
		return dao.getTopFromResource();
	}

}
