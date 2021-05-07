package cst438.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.City;
import cst438.domain.CityInfo;
import cst438.domain.CityRepository;
import cst438.service.CityService;
@RestController
public class CityRestController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CityService CityService;
	
	@GetMapping("/api/cities/{name}")
	public ResponseEntity<CityInfo> cityInfo(@PathVariable("name")String cityName){
		
		List<City> cities = cityRepository.findByName(cityName);
		if(cities.size() == 0) {
			return new ResponseEntity<CityInfo>( HttpStatus.NOT_FOUND);
			
		} else {
			
			CityInfo city=CityService.getCityInfo(cityName);
					
			return new ResponseEntity<CityInfo>(city, HttpStatus.OK);
		}
	}
	

}
