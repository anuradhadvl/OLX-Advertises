package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.AdvertisementEntity;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Integer>{
	
	List<AdvertisementEntity>findByTitle(String title);
	List<AdvertisementEntity>findByCategory(int category);
	List<AdvertisementEntity>findByTitleAndCategory(String title, int category);
	List<AdvertisementEntity>findByDescription(String description);
	List<AdvertisementEntity>findByPrice(double price);
	
	@Query(value = "SELECT ae from AdvertisementEntity ae WHERE ae.title LIKE %:title%")
	List<AdvertisementEntity>findByTitleLike(String title);
	
	List<AdvertisementEntity> findByOrderByTitleAsc();
	List<AdvertisementEntity> findByOrderByTitleDesc();
	
	@Query(value="SELECT * FROM advertises ORDER BY price", nativeQuery = true)
	List<AdvertisementEntity> sortProductsByPriceDesc();

}
