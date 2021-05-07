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
import cst438.domain.CityInfo;
import cst438.domain.CityRepository;
import cst438.domain.Country;
import cst438.domain.CountryRepository;
import cst438.service.CityService;
import cst438.service.WeatherService;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(CityServiceTest.class)
public class CityServiceTest {
	
	@MockBean
	private CountryRepository countryRepository;
	
	@MockBean
	private CityRepository cityRepository;
	
	
	@MockBean
	private WeatherService weatherService;
	
	@MockBean
	private CityService CityService;
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
	City city = new City(1, "TestCity", "DistrictTest", 100000, country);
	List<City> cities = new ArrayList<City>();
	cities.add(city);

		
	given(cityRepository.findByName("TestCity")).willReturn(cities);


	MockHttpServletResponse response = mvc.perform(get("/city/TestCity")).andReturn().getResponse();
			


	assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	

	City cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
	
	City expectedResult = new  City(1, "TestCity", "DistrictTest", 100000, country);

	assertThat(cityResult).isEqualTo(expectedResult);
	}
	
}


