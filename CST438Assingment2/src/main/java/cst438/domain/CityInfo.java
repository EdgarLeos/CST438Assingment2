package cst438.domain;

public class CityInfo {
	
	private int id;
	private String name;
	private int population;
	private double temp;
	private long time;
	private int timezone;
	private String countryCode;
	private String countryName;
	private String district; 

	public CityInfo() {}
	
	public CityInfo(int id, String name, int population, double temp, long time, int timezone, String countryCode,
			String countryName, String district) {
		super();
		this.id = id;
		this.name = name;
		this.population = population;
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityInfo other = (CityInfo) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population != other.population)
			return false;
		if (Double.doubleToLongBits(temp) != Double.doubleToLongBits(other.temp))
			return false;
		if (time != other.time)
			return false;
		if (timezone != other.timezone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CityInfo [id=" + id + ", name=" + name + ", population=" + population + ", temp=" + temp + ", time="
				+ time + ", timezone=" + timezone + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ ", district=" + district + "]";
	}



	
	
}
	