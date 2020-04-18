
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.UserAccount;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     17 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
public interface UserAccountService {

	public List<UserAccount> findAll();

	public UserAccount findById(int theId);

	public void save(UserAccount userAccount);

	public void deleteById(int theId);

}
