
package com.spring.crowdfunding.navsam.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crowdfunding.navsam.dao.UserAccountRepository;
import com.spring.crowdfunding.navsam.entity.UserAccount;
import com.spring.crowdfunding.navsam.service.UserAccountService;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	private UserAccountRepository userAccountRepository;

	@Override
	public List<UserAccount> findAll() {
		List<UserAccount> usersList = getUserAccountRepository().findAll();
		return usersList;
	}

	@Override
	public UserAccount findById(final int theId) {
		Optional<UserAccount> userAccount = getUserAccountRepository().findById(theId);

		if (userAccount.isPresent()) {
			return userAccount.get();
		} else {
			throw new RuntimeException("Did not find userAccount id - " + theId);
		}
	}

	@Override
	public void save(final UserAccount userAccount) {
		getUserAccountRepository().save(userAccount);

	}

	@Override
	public void deleteById(final int theId) {
		getUserAccountRepository().deleteById(theId);

	}

	@Autowired
	public void setUserAccountRepository(final UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

}
