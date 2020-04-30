
package com.spring.crowdfunding.navsam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crowdfunding.navsam.constants.AuthoritiesConstants;
import com.spring.crowdfunding.navsam.entity.Organisation;
import com.spring.crowdfunding.navsam.service.OrganisationService;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@AllArgsConstructor
@RestController
@RequestMapping(path = "/organisation")
public class OrganisationController {

	private OrganisationService organisationService;

	@GetMapping("/allorganisations")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public List<Organisation> listAllOrganisations() {
		return getOrganisationService().findAll();
	}

	@GetMapping("/getOrganisation/{theId}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
	public Organisation getOrganisation(@PathVariable final int theId) {
		Organisation organisation = getOrganisationService().findById(theId);
		if (organisation == null) {
			throw new RuntimeException("Organisation ID not found :" + theId);
		}
		return organisation;
	}

	@PostMapping("/addParticipant")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public Organisation addOrganisation(@RequestBody final Organisation theOrganisation) {
		// set id to zero to save as a new ID instead
		// of an update
		theOrganisation.setId(0);
		getOrganisationService().save(theOrganisation);
		return theOrganisation;
	}

	@PutMapping("/updateOrganisation")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public Organisation updateOrganisation(@RequestBody @Valid final Organisation theOrganisation) {
		getOrganisationService().save(theOrganisation);
		return theOrganisation;
	}

	@DeleteMapping("deleteOrganisation/{theId}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public String deleteOrganisation(@PathVariable final int theId) {
		Organisation theOrganisation = getOrganisationService().findById(theId);
		if (theOrganisation == null) {
			throw new RuntimeException("The Organisation with id : " + theId + " not found.");
		}

		getOrganisationService().deleteById(theId);
		return "The Organisation with Id " + theId + " deleted";
	}

	@Autowired
	public void setOrganisationService(final OrganisationService organisationService) {
		this.organisationService = organisationService;
	}
}
