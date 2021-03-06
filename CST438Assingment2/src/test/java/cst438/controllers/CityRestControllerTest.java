package cst438.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.domain.City;
import cst438.domain.CityInfo;
import cst438.domain.CityRepository;
import cst438.domain.Country;
import cst438.service.CityService;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {
	
	@MockBean
	private CityService CityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<CityInfo> jsonCityAttempt;
	
	@BeforeEach
	public void setUPEach() {
		MockitoAnnotations.initMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void test1() throws Exception{
		
		Country country = new Country("TST", "Test Country");
		City city = new City(1, "TestCity1", "DistrictTest", 100000, country);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		
		given(cityRepository.findByName("TestCity1")).willReturn(cities);
		
		CityInfo city1 = new CityInfo(1, "TestCity1", 50, "80.0 °F", "05.59 PM", "USA,","United States", "DistrictTest");
				
		given(CityService.getCityInfo("TestCity1")).willReturn(new CityInfo(1, "TestCity1", 50, "80.0 °F", "05.59 PM", "USA,","United States", "DistrictTest"));
		MockHttpServletResponse response = mvc.perform(get("/cities/TestCity1")).andReturn().getResponse();
		
		CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		CityInfo expectedResult = new CityInfo(1, "TestCity1", 50, "80.0 °F", "05.59 PM", "USA,","United States", "DistrictTest");
		
		expectedResult.setTemp( "80.0 °F");
		
		assertThat(cityResult).isEqualTo(expectedResult);
	}

}
