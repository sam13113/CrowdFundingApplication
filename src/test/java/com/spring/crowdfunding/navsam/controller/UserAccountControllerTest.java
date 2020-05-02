
package com.spring.crowdfunding.navsam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

	@Test
	public void testfindAllUsers() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(getUserAccountService().findAll()).thenReturn(getAllTestUsersAsList());

		List<UserAccount> responseEntity = getUserAccountController().listAllUsers();

		assertEquals(responseEntity.size(), getAllTestUsersAsList().size());
	}

	private List<UserAccount> getAllTestUsersAsList() {
		List<UserAccount> allTestUsers = new ArrayList<>();

		UserAccount userAccount1 = new UserAccount();

		userAccount1.setId(1);
		userAccount1.setFirstName("Lokesh1");
		userAccount1.setLastName("Gupta1");
		userAccount1.setUserName("Guptu1");
		userAccount1.setPassword("password1");
		userAccount1.setEmail("howtodoinjava1@gmail.com");
		userAccount1.setProjectsSupported(2);
		userAccount1.setTotalAmount(2650.3);
		userAccount1.setCountry(getATestCountry());
		userAccount1.setUserRoles(getaTestUserRoles());
		userAccount1.setCommentsList(getATestCommentsList());
		userAccount1.setProjectInvestorList(getATestInvestorList());

		UserAccount userAccount2 = new UserAccount();

		userAccount2.setId(2);
		userAccount2.setFirstName("Lokesh2");
		userAccount2.setLastName("Gupta2");
		userAccount2.setUserName("Guptu2");
		userAccount2.setPassword("password2");
		userAccount2.setEmail("howtodoinjava2@gmail.com");
		userAccount2.setProjectsSupported(2);
		userAccount2.setTotalAmount(2650.3);
		userAccount2.setCountry(getATestCountry());
		userAccount2.setUserRoles(getaTestUserRoles());
		userAccount2.setCommentsList(getATestCommentsList());
		userAccount2.setProjectInvestorList(getATestInvestorList());

		UserAccount userAccount3 = new UserAccount();

		userAccount3.setId(3);
		userAccount3.setFirstName("Lokesh3");
		userAccount3.setLastName("Gupta3");
		userAccount3.setUserName("Guptu3");
		userAccount3.setPassword("password3");
		userAccount3.setEmail("howtodoinjava3@gmail.com");
		userAccount3.setProjectsSupported(2);
		userAccount3.setTotalAmount(2650.3);
		userAccount3.setCountry(getATestCountry());
		userAccount3.setUserRoles(getaTestUserRoles());
		userAccount3.setCommentsList(getATestCommentsList());
		userAccount3.setProjectInvestorList(getATestInvestorList());

		allTestUsers.add(userAccount1);
		allTestUsers.add(userAccount2);
		allTestUsers.add(userAccount3);
		return allTestUsers;
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
