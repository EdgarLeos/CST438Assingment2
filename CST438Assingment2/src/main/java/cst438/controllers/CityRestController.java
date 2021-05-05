package cst438.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cst438.domain.City;
import cst438.domain.CityRepository;
import cst438.domain.TimeAndTemp;
import cst438.weather.WeatherService;
@RestController
public class CityRestController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/city/{name}")
	public ResponseEntity<City> cityInfo(@PathVariable("name")String name){
		
		List<City> cities = cityRepository.findByName(name);
		if(cities.size() == 0) {
			return new ResponseEntity<City>( HttpStatus.NOT_FOUND);
			
		} else {
			
			City city=cities.get(0);
			
			TimeAndTemp cityWeather = weatherService.getTempAndTime(name);
			
			double tempF = Math.round((cityWeather.getTemp() - 273.15)* 9.0/5.0 +32.0);
			cityWeather.setTemp(tempF);
			city.setWeather(cityWeather);
			
			return new ResponseEntity<City>(city, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/city/{name}")
	public ResponseEntity<City> deleteCity(@PathVariable("name") String name){
		
		List <City> cities = cityRepository.findByName(name);
		if(cities.size() == 0) {
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		} else {
			for(City c :cities) {
				cityRepository.delete(c);
			}
		}
		return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
	}

}
