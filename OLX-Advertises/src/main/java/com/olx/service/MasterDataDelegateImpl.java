package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.olx.service.MasterDataDelegate;

@Service
public class MasterDataDelegateImpl implements MasterDataDelegate{
	
	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
		
	}
	
	@Override
	public String getStatusById(int statusId) {
		String statusText= this.restTemplate.getForObject("http://localhost:9001/olx/advertise/status/"+statusId, String.class);
		return statusText;
	}
	
	public String getCategoryById(int categoryId) {
		String categoryText= this.restTemplate.getForObject("http://localhost:9001/olx/advertise/category/"+categoryId, String.class);
		return categoryText;
	}

}
