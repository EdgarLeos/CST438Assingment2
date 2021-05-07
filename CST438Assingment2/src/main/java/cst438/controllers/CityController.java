package cst438.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cst438.domain.CityInfo;
import cst438.service.CityService;



@Controller
public class CityController {
	
	@Autowired
	CityService CityService;
	
	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String cityName, Model model) {
		CityInfo city = CityService.getCityInfo(cityName);
		model.addAttribute("city", city);
		return "city_show";
	}

}
