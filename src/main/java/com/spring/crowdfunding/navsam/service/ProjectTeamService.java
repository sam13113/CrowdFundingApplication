
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.controller.vm.ProjectTeamVM;
import com.spring.crowdfunding.navsam.entity.ProjectTeam;

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
public interface ProjectTeamService {

	public List<ProjectTeam> findAll();

	public ProjectTeam findById(int theId);

	public void save(ProjectTeamVM theProjectTeam);

	public void deleteById(int theId);

}
