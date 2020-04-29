
package com.spring.crowdfunding.navsam.entity;

import java.time.LocalDate;
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
* 01   Sarat     18 04 2020      New Class
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
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "project_name")
	private String projectName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@Column(name = "project_description")
	private String projectDescription;

	@Column(name = "project_location")
	private String projectLocation;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "goal")
	private double goal;

	@Column(name = "pledged")
	private double pledged;

	@Column(name = "investors")
	private int investors;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private ProjectStatus projectStatus;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private List<Comment> commentList;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private List<Update> updateList;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private List<ProjectStatusHistory> projectStatusHistoryList;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private List<Material> materialsList;

	public Project(final String projectName, final Organisation organisation, final String projectDescription,
			final String projectLocation, final LocalDate startDate, final LocalDate endDate, final double goal,
			final double pledged, final int investors) {
		this.projectName = projectName;
		this.organisation = organisation;
		this.projectDescription = projectDescription;
		this.projectLocation = projectLocation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goal = goal;
		this.pledged = pledged;
		this.investors = investors;
	}

}
