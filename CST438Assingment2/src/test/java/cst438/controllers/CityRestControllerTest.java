package cst438.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.domain.City;
import cst438.domain.CityRepository;
import cst438.domain.CityWeather;
import cst438.domain.Country;
import cst438.weather.WeatherService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {
	
	@MockBean
	private WeatherService weatherService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<City> jsonCityAttempt;
	
	@BeforeEach
	public void setUPEach() {
		MockitoAnnotations.initMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void test1() throws Exception{
		
		Country country = new Country("TST", "Test Country");
		City city = new City (1, "TestCity", "DistrictTest", 10000, country);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		
		given(weatherService.getWeather("TestCity")).willReturn(new CityWeather(300, "cloudy"));
		
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		MockHttpServletResponse response = mvc.perform(get("/city/TestCity")).andReturn().getResponse();
		
		City cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		City expectedResult = new City(1, "TestCity", "DistrictTest", 10000, country);
		
		expectedResult.setWeather(new CityWeather(80, "cloudy"));
		
		assertThat(cityResult).isEqualTo(expectedResult);
	}

}
