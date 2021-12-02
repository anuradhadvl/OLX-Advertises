package com.olx.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.olx.dto.Advertisement;
import com.olx.entity.AdvertisementEntity;

public interface AdvertiseService {
	
	public  Advertisement createNewAdvertisement(@RequestHeader("auth-Token")String authToken,@RequestBody Advertisement advertisement );
	//public  Advertisement createNewAdvertisement(@RequestBody Advertisement advertisement );
	public Advertisement updateAdvertisement(@PathVariable("id") int advId, @RequestBody Advertisement advertisement,@RequestHeader("auth-Token") String authToken);
	public List<Advertisement> getAllAdvertisements(@RequestHeader("auth-Token") String authToken);
	public Advertisement getAdvertisementById(@PathVariable("id") int advId, @RequestHeader("auth-Token") String authToken);
	public boolean deleteByAdvertisementId(@PathVariable("id") int advId, @RequestHeader("auth-Token") String authToken);
	public Advertisement getAdvertisementByANewPerson(@PathVariable("id") int advId, @RequestHeader("auth-Token") String authToken);
	public List<Advertisement> findByTitle(String title);
	public List<Advertisement>findByCategory(int category);
	public List<Advertisement>findByTitleAndCategory(String title, int category);
	public List<Advertisement>findByDescription(String description);
	public List<Advertisement>findByPrice(double price);
	public List<Advertisement>findByTitleLike(String title);
	public List<Advertisement> findByOrderByTitle(String sortType);
	public List<Advertisement> findByPage(int startIndex, int records) ;
	List<Advertisement> sortProductsByPriceDesc();

	

}
