package cst438.service;


import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cst438.domain.*;

@Service
public class CityService {

		@Autowired
		private CityRepository cityRepository;
		
		@Autowired
		private WeatherService weatherService;
		
		public CityInfo getCityInfo(String cityName) {
			List<City> cities = cityRepository.findByName(cityName);
			City city = cities.get(0);
			TimeAndTemp cityWeather = weatherService.getTempAndTime(cityName);
			double tempF = Math.round((cityWeather.getTemp() - 273.15)* 9.0/5.0 +32.0);
			cityWeather.setTemp(tempF);
			city.setTimeAndTemp(cityWeather);
			
			return new CityInfo(city.getID(), city.getName(), city.getPopulation(), city.getTimeAndTemp().getTemp(), city.getTimeAndTemp().getTime(),city.getTimeAndTemp().getTimezone(),city.getCountry().getCode(),city.getCountry().getName(),city.getDistrict());

		}
}
