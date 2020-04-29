
package com.spring.crowdfunding.navsam.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crowdfunding.navsam.dao.OrganisationRepository;
import com.spring.crowdfunding.navsam.entity.Organisation;
import com.spring.crowdfunding.navsam.service.OrganisationService;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@Service
@AllArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {

	private OrganisationRepository organisationRepository;

	@Override
	public List<Organisation> findAll() {
		List<Organisation> orgList = getOrganisationRepository().findAll();
		return orgList;
	}

	@Override
	public Organisation findById(final int theId) {
		Optional<Organisation> theOrganisation = getOrganisationRepository().findById(theId);

		if (theOrganisation.isPresent()) {
			return theOrganisation.get();
		} else {
			throw new RuntimeException("Did not find Organisation id - " + theId);
		}
	}

	@Override
	public void save(final Organisation theOrganisation) {

		getOrganisationRepository().save(theOrganisation);

	}

	@Override
	public void deleteById(final int theId) {
		getOrganisationRepository().deleteById(theId);

	}

	@Autowired
	public void setOrganisationRepository(final OrganisationRepository organisationRepository) {
		this.organisationRepository = organisationRepository;
	}

}
