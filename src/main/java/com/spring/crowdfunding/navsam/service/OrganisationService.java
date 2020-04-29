
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Organisation;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     26 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
public interface OrganisationService {

	public List<Organisation> findAll();

	public Organisation findById(int theId);

	public void save(Organisation theOrganisation);

	public void deleteById(int theId);

}
