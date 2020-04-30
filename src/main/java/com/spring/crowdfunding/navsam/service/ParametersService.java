
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Parameters;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     30 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
public interface ParametersService {

	public List<Parameters> findAll();

	public Parameters findById(int theId);

	public void save(Parameters theParameters);

	public void deleteById(int theId);

}
