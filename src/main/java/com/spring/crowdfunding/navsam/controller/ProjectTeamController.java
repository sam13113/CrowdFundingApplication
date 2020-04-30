
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
import com.spring.crowdfunding.navsam.controller.vm.ProjectTeamVM;
import com.spring.crowdfunding.navsam.entity.ProjectTeam;
import com.spring.crowdfunding.navsam.service.ProjectTeamService;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     23 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
@Getter
@AllArgsConstructor
@RestController
@RequestMapping(path = "/teams")
public class ProjectTeamController {

	private ProjectTeamService projectTeamService;

	@GetMapping("/allProjectTeams")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
	public List<ProjectTeam> listAllProjectTeams() {
		return getProjectTeamService().findAll();
	}

	@GetMapping("/getTeam/{theId}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
	public ProjectTeam getProjectTeam(@PathVariable final int theId) {
		ProjectTeam projectTeam = getProjectTeamService().findById(theId);
		if (projectTeam == null) {
			throw new RuntimeException("Project ID not found : " + theId);
		}
		return projectTeam;
	}

	@PostMapping("/addProjectTeam")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ProjectTeamVM addProjectTeam(@RequestBody final ProjectTeamVM theProjectTeamVM) {
		// set id to zero to save as a new ID instead
		// of an update
		theProjectTeamVM.setId(0);
		getProjectTeamService().save(theProjectTeamVM);
		return theProjectTeamVM;
	}

	@PutMapping("/updateProjectTeam")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ProjectTeamVM updateProjectTeam(@RequestBody @Valid final ProjectTeamVM theProjectTeamVM) {
		getProjectTeamService().save(theProjectTeamVM);
		return theProjectTeamVM;
	}

	@DeleteMapping("deleteProjectTeam/{theId}")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public String deleteProjectTeams(@PathVariable final int theId) {
		ProjectTeam theProjectTeam = getProjectTeamService().findById(theId);
		if (theProjectTeam == null) {
			throw new RuntimeException("The Project Team with id : " + theId + " not found.");
		}

		getProjectTeamService().deleteById(theId);
		return "The Project with Id " + theId + " deleted";
	}

	@Autowired
	public void setProjectTeamService(final ProjectTeamService projectTeamService) {
		this.projectTeamService = projectTeamService;
	}

}
