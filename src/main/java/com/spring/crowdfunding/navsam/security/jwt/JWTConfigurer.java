
package com.spring.crowdfunding.navsam.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@AllArgsConstructor
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private TokenProvider tokenProvider;

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		JWTFilter customFilter = new JWTFilter(getTokenProvider());
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	public void setTokenProvider(final TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
}
