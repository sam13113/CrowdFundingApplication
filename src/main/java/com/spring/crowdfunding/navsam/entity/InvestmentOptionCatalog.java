
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
@Table(name = "investment_option_catalog")
public class InvestmentOptionCatalog implements Serializable {

	private static final long serialVersionUID = 1055791144766205240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "option_name")
	private String optionName;

	@Column(name = "fund_min")
	private double minFund;

	@Column(name = "fund_max")
	private double maxFund;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "investment_option_catalog_id")
	private List<ProjectInvestmentOption> projectInvestmentOptionList;

}
