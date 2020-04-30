
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.ProjectStatusHistory;

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
public interface ProjectStatusHistoryService {

	public List<ProjectStatusHistory> findAll();

	public ProjectStatusHistory findById(int theId);

	public void save(ProjectStatusHistory theProjectStatusHistory);

	public void deleteById(int theId);

}
