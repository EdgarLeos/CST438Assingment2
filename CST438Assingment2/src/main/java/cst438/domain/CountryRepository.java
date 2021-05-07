package cst438.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	Country findByCode(String code);

}
