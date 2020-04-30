
package com.spring.crowdfunding.navsam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crowdfunding.navsam.constants.AuthoritiesConstants;
import com.spring.crowdfunding.navsam.entity.Country;
import com.spring.crowdfunding.navsam.entity.UserAccount;
import com.spring.crowdfunding.navsam.service.CountryService;
import com.spring.crowdfunding.navsam.service.MailService;
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
@AllArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserAccountController {

	private UserAccountService userAccountService;
	private CountryService CountryService;
	private MailService mailService;

	@GetMapping("/allUsers")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public List<UserAccount> listAllUsers() {
		return getUserAccountService().findAll();
	}

	@GetMapping("/getUser/{theId}")
	public UserAccount getUserAccount(@PathVariable final int theId) {
		UserAccount user = getUserAccountService().findById(theId);
		if (user == null) {
			throw new RuntimeException("UserAccount ID not found :" + theId);
		}
		return user;
	}

	@PostMapping("/addUser")
	public UserAccount addUserAccount(@RequestBody final UserAccount theUserAccount) {
		// set id to zero to save as a new ID instead
		// of an update
		theUserAccount.setId(0);

		Country theCountry = getCountryService().getCountryfromDb(theUserAccount.getCountry());
		theUserAccount.setCountry(theCountry);
		getUserAccountService().save(theUserAccount);
		mailService.sendCreationEmail(theUserAccount);
		return theUserAccount;
	}

	@PutMapping("/updateUser")
	public UserAccount updateUserAccount(@RequestBody final UserAccount theUserAccount) {

		Country theCountry = getCountryService().getCountryfromDb(theUserAccount.getCountry());
		theUserAccount.setCountry(theCountry);
		getUserAccountService().save(theUserAccount);
		return theUserAccount;
	}

	@DeleteMapping("/deleteUser/{theId}")
	public String deleteUserAccount(@PathVariable final int theId) {

		UserAccount theUser = getUserAccountService().findById(theId);
		if (theUser == null) {
			throw new RuntimeException("the User Id is not valid : " + theId);
		}
		getUserAccountService().deleteById(theId);

		return "The User with Id " + theId + " deleted";

	}

	@Autowired
	public void setUserAccountService(final UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	@Autowired
	public void setCountryService(final CountryService countryService) {
		this.CountryService = countryService;
	}

	@Autowired
	public void setMailService(final MailService mailService) {
		this.mailService = mailService;
	}

}
