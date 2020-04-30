
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.InvestmentOptionCatalog;
import com.spring.crowdfunding.navsam.entity.Material;

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
public interface MaterialService {

	public List<Material> findAll();

	public InvestmentOptionCatalog findById(int theId);

	public void save(Material theMaterial);

	public void deleteById(int theId);

}
