
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Project;

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
public interface ProjectService {

	public List<Project> findAll();

	public Project findById(int theId);

	public void save(Project theProject);

	public void deleteById(int theId);

}
