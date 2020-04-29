
package com.spring.crowdfunding.navsam.entity;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
//@EntityListeners(PreventAnyUpdate.class)
@Table(name = "user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -5268574771083561660L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty(message = "Please provide a id")
	@Column(name = "id")
	private int id;

	@Column(name = "role_name")
	@NotEmpty(message = "Please provide a role")
	private String roleName;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, // mappedBy = "userRoles",
			cascade =
	{ CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "user_role_map", joinColumns = @JoinColumn(name = "user_role_id"), inverseJoinColumns = @JoinColumn(name = "user_account_id"))
	private Set<UserAccount> useraccounts;

	public UserRole(final String roleName) {
		this.roleName = roleName;
	}

	public void Adduseraccount(final UserAccount useraccount) {
		if (useraccounts == null) {
			useraccounts = new HashSet<UserAccount>();
		}
		useraccounts.add(useraccount);
	}


}
