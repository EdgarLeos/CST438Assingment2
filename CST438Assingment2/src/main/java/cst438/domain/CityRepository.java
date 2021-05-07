package cst438.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<City, Integer> {
	
	List<City> findByName(String name);

}