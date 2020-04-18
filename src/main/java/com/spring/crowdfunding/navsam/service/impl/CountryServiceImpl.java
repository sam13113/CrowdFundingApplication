
package com.spring.crowdfunding.navsam.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crowdfunding.navsam.dao.CountryRepository;
import com.spring.crowdfunding.navsam.entity.Country;
import com.spring.crowdfunding.navsam.service.CountryService;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     18 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
@Getter
@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

	private CountryRepository countryRepository;

	@Override
	public List<Country> findAll() {
		List<Country> countryList = new ArrayList<>();
		countryList = getCountryRepository().findAll();
		return countryList;
	}

	@Override
	public Country findById(final int theId) {

		Optional<Country> country = getCountryRepository().findById(theId);
		if (country.isPresent()) {
			return country.get();
		} else {
			throw new RuntimeException("Did not find country id - " + theId);
		}
	}

	@Override
	public void save(final Country country) {
		getCountryRepository().save(country);
	}

	@Override
	public void deleteById(final int theId) {
		getCountryRepository().deleteById(theId);
	}

	@Override
	public Country getCountryfromDb(final Country theCountry) {
		if (theCountry != null) {
		List<Country> allCountriesInDb = findAll();
		for (Country country : allCountriesInDb) {
			if (country.getCountryName().equalsIgnoreCase(theCountry.getCountryName())) {
				return country;
			}
		}
		// set id to zero to save as a new ID instead
		// of an update
		theCountry.setId(0);
		save(theCountry);
		}
		return theCountry;
	}

	@Autowired
	public void setCountryRepository(final CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

}
