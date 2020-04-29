
package com.spring.crowdfunding.navsam.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     29 04 2020      New Class
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
@Table(name = "project_investment_option")
public class ProjectInvestmentOption implements Serializable {

	private static final long serialVersionUID = -1290866897841303883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private List<Project> projectList;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private InvestmentOptionCatalog investmentCatalog;

	@Column(name = "option_name")
	private String optionName;

	@Column(name = "option_description")
	private String optionDescription;

	public ProjectInvestmentOption(final String optionName, final String optionDescription) {
		this.optionName = optionName;
		this.optionDescription = optionDescription;
	}

}
