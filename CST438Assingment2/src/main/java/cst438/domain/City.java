package cst438.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;

@Entity
@Table(name = "city")
public class City {
	
	@Id
	private int ID;
	private String name;
	private String district;
	private int population;
	
	@Transient
	TimeAndTemp weather;
	
	@ManyToOne
	@JoinColumn(name= "countrycode", referencedColumnName = "code")
	private Country country;
	
	public City() {	}

	public City(int iD, String name, String district, int population, Country country) {
		super();
		ID = iD;
		this.name = name;
		this.district = district;
		this.population = population;
		this.country = country;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public TimeAndTemp getWeather() {
		return weather;
	}

	public void setWeather(TimeAndTemp weather) {
		this.weather = weather;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}



	
	

}