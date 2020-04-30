
package com.spring.crowdfunding.navsam.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crowdfunding.navsam.controller.vm.ProjectTeamVM;
import com.spring.crowdfunding.navsam.dao.ParticipantRepository;
import com.spring.crowdfunding.navsam.dao.ProjectRepository;
import com.spring.crowdfunding.navsam.dao.ProjectTeamRepository;
import com.spring.crowdfunding.navsam.entity.Participant;
import com.spring.crowdfunding.navsam.entity.Project;
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
@Service
@AllArgsConstructor
@Getter
public class ProjectTeamServiceImpl implements ProjectTeamService {

	private ProjectTeamRepository projectTeamRepository;
	private ParticipantRepository participantRepository;
	private ProjectRepository projectRepository;

	@Override
	public List<ProjectTeam> findAll() {
		List<ProjectTeam> projectTeam = getProjectTeamRepository().findAll();
		return projectTeam;
	}

	@Override
	public ProjectTeam findById(final int theId) {
		Optional<ProjectTeam> projectTeam = getProjectTeamRepository().findById(theId);
		if (projectTeam.isPresent()) {
			return projectTeam.get();
		} else {
			throw new RuntimeException("Did not find a project team by the given id : " + theId);
		}
	}

	@Override
	public void save(final ProjectTeamVM theProjectTeamVM) {
		ProjectTeam theProjectTeam = new ProjectTeam();
		theProjectTeam.setId(theProjectTeamVM.getId());
		theProjectTeam.setProjectRole(theProjectTeamVM.getProjectRole());
		theProjectTeam.setParticipantResponsibilities(theProjectTeamVM.getParticipantResponsibilities());
		Optional<Participant> participant = getParticipantRepository().findById(theProjectTeamVM.getParticipantId());
		theProjectTeam.setParticipant(participant.get());

		Optional<Project> project = getProjectRepository().findById(theProjectTeamVM.getProjectId());
		theProjectTeam.setProject(project.get());

		theProjectTeam.setParticipantResponsibilities(theProjectTeamVM.getParticipantResponsibilities());

		getProjectTeamRepository().save(theProjectTeam);
	}

	@Override
	public void deleteById(final int theId) {
		getProjectTeamRepository().deleteById(theId);
	}

	@Autowired
	public void setProjectTeamRepository(final ProjectTeamRepository projectTeamRepository) {
		this.projectTeamRepository = projectTeamRepository;
	}

	@Autowired
	public void setParticipantRepository(final ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	@Autowired
	public void setProjectRepository(final ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

}
