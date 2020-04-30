
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.InvestmentOptionCatalog;

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
public interface InvestmentOptionCatalogService {

	public List<InvestmentOptionCatalog> findAll();

	public InvestmentOptionCatalog findById(int theId);

	public void save(InvestmentOptionCatalog theInvestmentOptionCatalog);

	public void deleteById(int theId);

}
