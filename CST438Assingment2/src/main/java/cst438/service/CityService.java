package cst438.service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			System.out.print(cityWeather.getTime()+cityWeather.getTimezone()*1000);
			double tempF = Math.round((cityWeather.getTemp() - 273.15) * 9.0/5.0 +32.0);
			cityWeather.setTemp(tempF);
			Date t = new Date();
			long localTime = t.getTime();
			long localOff= t.getTimezoneOffset()*60000; // why is this crossed out?
			long utc = localTime + localOff;
			long cityOff = utc+(1000*cityWeather.getTimezone());
			DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
	    	String dateString = dateFormat.format(new Date(cityOff)).toString();
			city.setTimeAndTemp(cityWeather);
			return new CityInfo(city.getID(),
					city.getName(), 
					city.getPopulation(),
					String.valueOf(city.getTimeAndTemp().getTemp()) + " °F", 
					dateString,  
					city.getCountry().getCode(),
					city.getCountry().getName(),
					city.getDistrict());

		}
}
