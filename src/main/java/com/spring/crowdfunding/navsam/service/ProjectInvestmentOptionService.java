
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.ProjectInvestmentOption;

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
public interface ProjectInvestmentOptionService {

	public List<ProjectInvestmentOption> findAll();

	public ProjectInvestmentOption findById(int theId);

	public void save(ProjectInvestmentOption theProjectInvestmentOption);

	public void deleteById(int theId);

}
