
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.ProjectStatus;

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
public interface ProjectStatusService {

	public List<ProjectStatus> findAll();

	public ProjectStatus findById(int theId);

	public void save(ProjectStatus theProjectStatus);

	public void deleteById(int theId);

}
