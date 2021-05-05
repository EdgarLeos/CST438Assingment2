package cst438.weather;


import cst438.domain.CityWeather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class WeatherService {

	private static final Logger Log = LoggerFactory.getLogger(WeatherService.class);
	
	private RestTemplate restTemplate;
	
	private String weatherUrl;
	private String apiKey;
	
	public WeatherService(@Value("${weather.url}") final String weatherUrl, @Value("${weather.apikey}") final String apiKey) {
		this.restTemplate = new RestTemplate();
		this.weatherUrl = weatherUrl;
		this.apiKey = apiKey;
	}
	
	public CityWeather getWeather(String cityName) {
		ResponseEntity<JsonNode> response = restTemplate.getForEntity(weatherUrl + "?q=" + cityName +"&appid="+ apiKey, JsonNode.class);
		JsonNode json = response.getBody();
		Log.info("Status code from weather server:" + response.getStatusCodeValue());
		double temp = json.get("main").get("temp").asDouble();
		String condition = json.get("weather").get(0).get("description").asText();
		
		return new CityWeather(temp, condition);
	}
	
}