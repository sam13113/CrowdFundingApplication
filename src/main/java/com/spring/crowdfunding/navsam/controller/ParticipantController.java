
package com.spring.crowdfunding.navsam.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.spring.crowdfunding.navsam.entity.Participant;
import com.spring.crowdfunding.navsam.service.ParticipantService;

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
@RequestMapping(path = "/participant")
public class ParticipantController {

	private ParticipantService participantService;

	@GetMapping("/allParticipants")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public List<Participant> listAllParticipants() {
		return getParticipantService().findAll();
	}

	@GetMapping("/getParticipant/{theId}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
	public Participant getParticipant(@PathVariable final int theId) {
		Participant participant = getParticipantService().findById(theId);
		if (participant == null) {
			throw new RuntimeException("Participant ID not found :" + theId);
		}
		return participant;
	}

	@PostMapping("/addParticipant")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public Participant addParticipant(@RequestBody final Participant theParticipant) {
		// set id to zero to save as a new ID instead
		// of an update
		theParticipant.setId(0);
		getParticipantService().save(theParticipant);
		return theParticipant;
	}

	@PutMapping("/updateParticipant")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public Participant updateProjectTeam(@RequestBody @Valid final Participant theParticipant) {
		getParticipantService().save(theParticipant);
		return theParticipant;
	}

	@DeleteMapping("deleteParticipant/{theId}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public String deleteProjectTeams(@PathVariable final int theId) {
		Participant theParticipant = getParticipantService().findById(theId);
		if (theParticipant == null) {
			throw new RuntimeException("The Participant with id : " + theId + " not found.");
		}

		getParticipantService().deleteById(theId);
		return "The Participant with Id " + theId + " deleted";
	}
}
