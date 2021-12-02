package com.olx.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.olx.dto.Advertisement;
import com.olx.entity.AdvertisementEntity;
import com.olx.exception.InvalidAdvertisementIDException;
import com.olx.repository.AdvertisementRepository;
import com.olx.utils.AdvertiseConverter;


@Service
public class AdvertiseServiceImpl implements AdvertiseService {
	
	@Autowired
	AdvertisementRepository advRepository ;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	MasterDataDelegate masterDataDelegate;
	
	
	
	
	@Override
	public  Advertisement createNewAdvertisement(@RequestHeader("auth-Token")String authToken,@RequestBody Advertisement advertisement ) {
		advertisement.setCreatedDate(LocalDate.now());
		advertisement.setModifiedDate(LocalDate.now());
		advertisement.setStatusId(1);
		String statusText = masterDataDelegate.getStatusById(advertisement.getStatusId());
		advertisement.setStatusText(statusText);
		String categoryText = masterDataDelegate.getCategoryById(advertisement.getCategory());
		advertisement.setCategoryText(categoryText);
		AdvertisementEntity advEntity = this.modelMapper.map(advertisement, AdvertisementEntity.class);
		advEntity = this.advRepository.save(advEntity);
		Advertisement advDto = this.modelMapper.map(advEntity, Advertisement.class);
		return advDto;
	}
	
	
	
	
	@Override
	public Advertisement updateAdvertisement(int advId, Advertisement advertisement, String authToken) {
		Optional<AdvertisementEntity> opAdvEntity = advRepository.findById(advId);
		if(opAdvEntity.isPresent()) { //Correct advId
			AdvertisementEntity advEntity = opAdvEntity.get();
			
			advertisement.setId(advId);
			advertisement.setCreatedDate(advEntity.getCreatedDate());
			advertisement.setModifiedDate(LocalDate.now());
			advEntity = this.modelMapper.map(advertisement, AdvertisementEntity.class);
			advEntity = advRepository.save(advEntity);
			Advertisement advDto = this.modelMapper.map(advEntity, Advertisement.class);
			return advDto;
		}
		throw new InvalidAdvertisementIDException(" "+advId );
	}
	
	public List<Advertisement> getAllAdvertisements(@RequestHeader("auth-Token") String authToken){
		List<AdvertisementEntity>advEntities = advRepository.findAll();
		List<Advertisement>advDtoList = new ArrayList<Advertisement>();
		
		for(AdvertisementEntity advEntity : advEntities) {		
			Advertisement advDto = this.modelMapper.map(advEntity, Advertisement.class);
			advDtoList.add(advDto);
		}
		return advDtoList;
	}
	
	public Advertisement getAdvertisementById(@PathVariable("id")int advId, @RequestHeader("auth-Token") String authToken) {
		Optional<AdvertisementEntity> opAdvEntity = advRepository.findById(advId);
		if(opAdvEntity.isPresent()) { //Correct advertisementId
			AdvertisementEntity advEntity = opAdvEntity.get();
			Advertisement advDto = this.modelMapper.map(advEntity, Advertisement.class);
			System.out.println("control came");
			return advDto;
		}
		throw new InvalidAdvertisementIDException(" "+advId );
	}
	
	public boolean deleteByAdvertisementId(@PathVariable("id") int advId, @RequestHeader("auth-Token") String authToken) {
		advRepository.deleteById(advId);
				 return true;

	}
	
	public Advertisement getAdvertisementByANewPerson(@PathVariable("id") int advId, @RequestHeader("auth-Token") String authToken) {
		Optional<AdvertisementEntity> opAdvEntity = advRepository.findById(advId);
		if(opAdvEntity.isPresent()) { //Correct advertisementId
			AdvertisementEntity advEntity = opAdvEntity.get();
			Advertisement advDto = this.modelMapper.map(advEntity, Advertisement.class);
			return advDto;
		}
		throw new InvalidAdvertisementIDException(" "+advId );
		 
		
	}
	
/*********converts AdvertisementEntity to Advertisement**********************/
private List<Advertisement> getAdvDtoList(List<AdvertisementEntity> advEntityList) {
		List<Advertisement> advDtoList = new ArrayList<Advertisement>();
		for(AdvertisementEntity advEntity: advEntityList) {
			Advertisement advDto = this.modelMapper.map(advEntity, Advertisement.class);
			advDtoList.add(advDto);
		}
		return advDtoList;
	}

/****************************FIND BY METHODS***************************************/
	@Override
	public List<Advertisement> findByTitle(String title) {
		List<AdvertisementEntity> advEntityList = advRepository.findByTitle(title);
		return getAdvDtoList(advEntityList);
	}




@Override
public List<Advertisement> findByCategory(int category) {
	List<AdvertisementEntity> advEntityList = advRepository.findByCategory(category);
	return getAdvDtoList(advEntityList);
	
}




@Override
public List<Advertisement> findByTitleAndCategory(String title, int category) {
	List<AdvertisementEntity> advEntityList = advRepository.findByTitleAndCategory(title, category);
	return getAdvDtoList(advEntityList);
}

@Override
public List<Advertisement> findByDescription(String description) {
	List<AdvertisementEntity> advEntityList = advRepository.findByDescription(description);
	return getAdvDtoList(advEntityList);
}


@Override
public List<Advertisement> findByPrice(double price) {
	List<AdvertisementEntity> advEntityList = advRepository.findByPrice(price);
	return getAdvDtoList(advEntityList);
}


@Override
public List<Advertisement> findByTitleLike(String title) {
	List<AdvertisementEntity> advEntityList = advRepository.findByTitleLike(title);
	return getAdvDtoList(advEntityList);
}




@Override
public List<Advertisement> findByOrderByTitle(String sortType) {
	List<AdvertisementEntity>advEntityList = null;
	if("ASC".equalsIgnoreCase(sortType)) {
		advEntityList = advRepository.findByOrderByTitleAsc();
	}
	if("DESC".equalsIgnoreCase(sortType)) {
		advEntityList = advRepository.findByOrderByTitleDesc();
	}
	return getAdvDtoList(advEntityList);
}

@Override
public List<Advertisement> findByPage(int startIndex, int records) {
	Pageable pageWithFewRecords = PageRequest.of(startIndex, records);
	Page<AdvertisementEntity> advEntityPage = advRepository.findAll(pageWithFewRecords);
	List<AdvertisementEntity> advEntityList = advEntityPage.getContent();
	return getAdvDtoList(advEntityList);
}


@Override
public List<Advertisement> sortProductsByPriceDesc() {
	List<AdvertisementEntity> advEntityList = advRepository.sortProductsByPriceDesc();
	return getAdvDtoList(advEntityList);
}
	

}
