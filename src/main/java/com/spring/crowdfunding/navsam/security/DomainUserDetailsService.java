
package com.spring.crowdfunding.navsam.security;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.crowdfunding.navsam.dao.UserAccountRepository;
import com.spring.crowdfunding.navsam.entity.UserAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     23 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
@Slf4j
@Getter
@NoArgsConstructor
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

	private UserAccountRepository UserAccountRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		  log.debug("Authenticating {}", username);
		  
		if (username != null) {
			UserDetails userDetails = getUserAccountRepository().findByUserName(username)
					.map(user -> createSpringSecurityUser(username, user))
					.orElseThrow(() -> new RuntimeException(
							"User with username " + username + " was not found in the database"));
			return userDetails;
		}
		return null;
	}

	private User createSpringSecurityUser(final String username, final UserAccount user) {
		List<GrantedAuthority> grantedAuthorities = user.getUserRoles().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getRoleName())).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}

	@Autowired
	public void setUserAccountRepository(final UserAccountRepository userAccountRepository) {
		UserAccountRepository = userAccountRepository;
	}

}
