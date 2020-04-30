
package com.spring.crowdfunding.navsam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.crowdfunding.navsam.entity.Comment;
import com.spring.crowdfunding.navsam.entity.Country;
import com.spring.crowdfunding.navsam.entity.ProjectInvestor;
import com.spring.crowdfunding.navsam.entity.UserAccount;
import com.spring.crowdfunding.navsam.entity.UserRole;
import com.spring.crowdfunding.navsam.service.impl.UserAccountServiceImpl;

import lombok.Getter;

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
@Getter
@ExtendWith(MockitoExtension.class)
public class UserAccountControllerTest {

	@InjectMocks
	private UserAccountController userAccountController;

	@Mock
	private UserAccountServiceImpl userAccountService;

	@Test
	public void testfindUserById() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(getUserAccountService().findById(1)).thenReturn(getATestUserAccount());

		UserAccount responseEntity = getUserAccountController().getUserAccount(1);

		assertEquals(responseEntity.getFirstName(), getATestUserAccount().getFirstName());
		assertEquals(responseEntity.getLastName(), getATestUserAccount().getLastName());
		assertEquals(responseEntity.getUserName(), getATestUserAccount().getUserName());
		assertEquals(responseEntity.getEmail(), getATestUserAccount().getEmail());
		assertEquals(responseEntity.getPassword(), getATestUserAccount().getPassword());
		assertEquals(responseEntity.getCountry(), getATestUserAccount().getCountry());
		assertEquals(responseEntity.getProjectsSupported(), getATestUserAccount().getProjectsSupported());
		assertEquals(responseEntity.getTotalAmount(), getATestUserAccount().getTotalAmount());
		assertEquals(responseEntity.getCommentsList(), getATestUserAccount().getCommentsList());
		assertEquals(responseEntity.getProjectInvestorList(), getATestUserAccount().getProjectInvestorList());

	}

	private UserAccount getATestUserAccount() {

		UserAccount userAccount = new UserAccount();

		userAccount.setId(1);
		userAccount.setFirstName("Lokesh");
		userAccount.setLastName("Gupta");
		userAccount.setUserName("Guptu");
		userAccount.setPassword("password");
		userAccount.setEmail("howtodoinjava@gmail.com");
		userAccount.setProjectsSupported(2);
		userAccount.setTotalAmount(2650.3);
		userAccount.setCountry(getATestCountry());
		userAccount.setUserRoles(getaTestUserRoles());
		userAccount.setCommentsList(getATestCommentsList());
		userAccount.setProjectInvestorList(getATestInvestorList());

		return userAccount;

	}

	private List<ProjectInvestor> getATestInvestorList() {
		return null;
	}

	private List<Comment> getATestCommentsList() {
		return null;
	}

	private Set<UserRole> getaTestUserRoles() {
		return null;
	}

	private Country getATestCountry() {
		return null;
	}

}
