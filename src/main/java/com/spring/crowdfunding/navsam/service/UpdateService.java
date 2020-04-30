
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Update;

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
public interface UpdateService {

	public List<Update> findAll();

	public Update findById(int theId);

	public void save(Update theUpdate);

	public void deleteById(int theId);

}
