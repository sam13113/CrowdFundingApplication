
package com.spring.crowdfunding.navsam.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable{

	private static final long serialVersionUID = -6821160177318689580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_name")
	@NotEmpty(message = "Please provide a user name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "projects_supported")
	private int projectsSupported;

	@Column(name = "total_amount")
	private double totalAmount;

	@ManyToOne(optional = false, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.REFRESH })
	@JoinTable(name = "user_role_map", joinColumns = @JoinColumn(name = "user_account_id"), inverseJoinColumns = @JoinColumn(name = "user_role_id"))
	private Set<UserRole> userRoles;

	public UserAccount(final String firstName, final String lastName, final String userName, final String password,
			final String email, final int projectsSupported, final double totalAmount, final Country country,
			final Set<UserRole> userRoles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.projectsSupported = projectsSupported;
		this.totalAmount = totalAmount;
		this.country = country;
		this.userRoles = userRoles;
		this.userRoles.forEach(role -> role.getUseraccounts().add(this));
	}

}
