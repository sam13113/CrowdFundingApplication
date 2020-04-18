
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Country;

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
public interface CountryService {
	public List<Country> findAll();

	public Country findById(int theId);

	public void save(Country country);

	public void deleteById(int theId);

	public Country getCountryfromDb(final Country theCountry);

}
