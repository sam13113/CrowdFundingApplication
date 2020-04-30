
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.ProjectInvestor;

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
public interface ProjectInvestorService {

	public List<ProjectInvestor> findAll();

	public ProjectInvestor findById(int theId);

	public void save(ProjectInvestor theProjectInvestor);

	public void deleteById(int theId);

}
