
package com.spring.crowdfunding.navsam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.crowdfunding.navsam.controller.vm.LoginVM;
import com.spring.crowdfunding.navsam.security.jwt.JWTFilter;
import com.spring.crowdfunding.navsam.security.jwt.TokenProvider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     22 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class UserJwtController {

	private AuthenticationManagerBuilder authenticationManagerBuilder;

	private TokenProvider tokenProvider;

	@PostMapping("/authenticate")
	public ResponseEntity<JWTToken> authenticateUser(@Valid @RequestBody final LoginVM loginVM) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginVM.getUserName(), loginVM.getPassword());

		Authentication authentication = getAuthenticationManagerBuilder().getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean rememberMe = loginVM.isRememberMe();
		String jwt = getTokenProvider().createToken(authentication, rememberMe);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Object to return as body in JWT Authentication.
	 */
	@Setter
	@AllArgsConstructor
	static class JWTToken {

		private String idToken;

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}
	}

	@Autowired
	public void setAuthenticationManagerBuilder(final AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	@Autowired
	public void setTokenProvider(final TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

}
