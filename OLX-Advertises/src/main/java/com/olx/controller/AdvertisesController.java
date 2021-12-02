package com.olx.controller;

import java.time.format.DateTimeFormatter;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.olx.dto.Advertisement;
import com.olx.exception.InvalidAdvertisementIDException;
import com.olx.service.AdvertiseService;



@RestController
@RequestMapping (value = "/olx")
public class AdvertisesController {
	
	@Autowired
	//@Qualifier("JPA_SERVICE")
	private AdvertiseService advertiseService;
	
	@ExceptionHandler(value= {InvalidAdvertisementIDException.class})
	public ResponseEntity<Object> handleAdvIdException(RuntimeException exception, WebRequest request){
		
		return new ResponseEntity<Object>("Local Exception Handler - Invalid AdvID",HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping(value="/advertise",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public  ResponseEntity<Advertisement> createNewAdvertisement(@RequestHeader("auth-Token")String authToken,@RequestBody Advertisement advertisement ) {
		advertisement =advertiseService.createNewAdvertisement(authToken, advertisement);
		return new ResponseEntity(advertisement, HttpStatus.CREATED);
	}
	

	@PutMapping(value="/advertisement/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertisement> updateAdvertisement(@PathVariable("id") int advId, @RequestBody Advertisement advertisement,@RequestHeader("auth-Token") String authToken) {
		advertisement=advertiseService.updateAdvertisement(advId, advertisement, authToken);
		return new ResponseEntity(advertisement, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/user/advertise", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertisement> getAllAdvertisements(@RequestHeader("auth-Token") String authToken) {
		List<Advertisement> advertisements = advertiseService.getAllAdvertisements(authToken);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	
	@GetMapping(value="/user/advertise/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable("id")int advId, @RequestHeader("auth-Token") String authToken) {
		Advertisement advertisement = advertiseService.getAdvertisementById(advId, authToken);
		return new ResponseEntity(advertisement, HttpStatus.FOUND);
	}
	
	@DeleteMapping(value="/user/advertise/{postId}")
	public ResponseEntity<Boolean> deleteByAdvertisementId(@PathVariable("postId") int advId, @RequestHeader("auth-Token") String authToken) {
		boolean idStatus = advertiseService.deleteByAdvertisementId(advId, authToken);
		return new ResponseEntity(idStatus, HttpStatus.OK);
	}
	
	@GetMapping(value="/advertise/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertisement> getAdvertisementByANewPerson(@PathVariable("id") int advId, @RequestHeader("auth-Token") String authToken) {
		Advertisement advertisement = advertiseService.getAdvertisementByANewPerson(advId, authToken);
		return new ResponseEntity(advertisement, HttpStatus.FOUND);
	}
	/*
	 12th search criteria-- not complete
	@GetMapping(value="/advertise/search/crietria", produces ={ MediaType.APPLICATION_JSON_VALUEMediaType.APPLICATION_XML_VALUE})
	public Advertisement getAdvertsiementBySearch(@RequestParam("id") int advId, @RequestParam("title") String title, @RequestParam("price") double price,@RequestParam("username") String username, @RequestParam("category") int category, @RequestParam("description") String description,@RequestHeader("auth-Token") String authToken) {
	   return null;
	}
	//13th search criteria -- needs to be implemented. 
	 *
	 */
	
	//**************FINDBY FUNCTIONS IMPLEMENTED******************************/
	
	@GetMapping(value="/advertise/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByTitle(@PathVariable("title") String title) {
		List<Advertisement> advertisements = advertiseService.findByTitle(title);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	

	@GetMapping(value="/advertise/category/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByCategory(@PathVariable("category") int category) {
		List<Advertisement> advertisements = advertiseService.findByCategory(category);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/advertise/title/{title}/category/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByTitleAndCategory(@PathVariable("title") String title, @PathVariable("category") int category) {
		List<Advertisement> advertisements = advertiseService.findByTitleAndCategory(title, category);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/advertise/description/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByDescription(@PathVariable("description") String description) {
		List<Advertisement> advertisements = advertiseService.findByDescription(description);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/advertise/price/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByPrice(@PathVariable("price") double price) {
		List<Advertisement> advertisements = advertiseService.findByPrice(price);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/advertise/title/like/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByTitleLike(@PathVariable("title") String title) {
		List<Advertisement> advertisements = advertiseService.findByTitleLike(title);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	

	@GetMapping(value="/advertise/title/sort/{sortType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsSortByTitle(@PathVariable("sortType") String sortType) {
		List<Advertisement> advertisements = advertiseService.findByOrderByTitle(sortType);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/advertise/page/{startIndex}/{records}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByPage(@PathVariable("startIndex") int startIndex, @PathVariable("records") int records) {
		List<Advertisement> advertisements = advertiseService.findByPage(startIndex, records);
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/advertise/sort/price", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertisement> getAdvertisementsByPriceDesc() {
		List<Advertisement> advertisements = advertiseService.sortProductsByPriceDesc();
		return new ResponseEntity(advertisements, HttpStatus.FOUND);
	}

}
