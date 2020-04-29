
package com.spring.crowdfunding.navsam.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crowdfunding.navsam.dao.ProjectRoleRepository;
import com.spring.crowdfunding.navsam.entity.ProjectRole;
import com.spring.crowdfunding.navsam.service.ProjectRoleService;

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
public class ProjectRoleServiceImpl implements ProjectRoleService {

	private ProjectRoleRepository projectRoleRepository;

	@Override
	public List<ProjectRole> findAll() {
		List<ProjectRole> rolesList = getProjectRoleRepository().findAll();
		return rolesList;
	}

	@Override
	public ProjectRole findById(final int theId) {
		Optional<ProjectRole> projectRole = getProjectRoleRepository().findById(theId);

		if (projectRole.isPresent()) {
			return projectRole.get();
		} else {
			throw new RuntimeException("Did not find projectRole id - " + theId);
		}
	}

	@Override
	public void save(final ProjectRole theProjectRole) {

		getProjectRoleRepository().save(theProjectRole);

	}

	@Override
	public void deleteById(final int theId) {
		getProjectRoleRepository().deleteById(theId);

	}

	@Autowired
	public void setProjectRoleRepository(final ProjectRoleRepository projectRoleRepository) {
		this.projectRoleRepository = projectRoleRepository;
	}

}
