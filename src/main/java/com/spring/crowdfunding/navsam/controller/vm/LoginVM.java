
package com.spring.crowdfunding.navsam.controller.vm;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@ToString
public class LoginVM {

	@NotNull
	@Size(min = 3, max = 20)
	private String userName;

	@NotNull
	@Size(min = 3, max = 20)
	private String password;

	@NotNull
	private boolean rememberMe;
}
