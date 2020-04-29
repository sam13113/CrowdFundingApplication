
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.ProjectRole;

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
public interface ProjectRoleService {

	public List<ProjectRole> findAll();

	public ProjectRole findById(int theId);

	public void save(ProjectRole theProjectRole);

	public void deleteById(int theId);

}
