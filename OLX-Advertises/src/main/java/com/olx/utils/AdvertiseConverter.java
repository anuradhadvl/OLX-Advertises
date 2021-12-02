package com.olx.utils;

import com.olx.entity.AdvertisementEntity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertisement;

@Service
public class AdvertiseConverter {
	@Autowired
	ModelMapper modelMapper;
	
	
	/*
	public static AdvertiseConverter getInstance() {
		if(advertiseConverter==null) {
			advertiseConverter = new AdvertiseConverter();
		}
		return advertiseConverter;
	}
	*/
	public Advertisement convertAdvertisementEntityIntoAdvertisementDto(AdvertisementEntity advEntity){
		Advertisement advDto= this.modelMapper.map(advEntity, Advertisement.class);
		/*advDto.setId(advEntity.getId());
		advDto.setCategory(advEntity.getCategory());
		advDto.setTitle(advEntity.getTitle());
		advDto.setPrice(advEntity.getPrice());
		advDto.setDescription(advEntity.getDescription());
		advDto.setStatus(advEntity.getStatus());
		advDto.setCreatedDate(advEntity.getCreatedDate());
		advDto.setModifiedDate(advEntity.getModifiedDate()); */
		return advDto;
		
	}

}
