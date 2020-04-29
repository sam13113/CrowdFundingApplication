
package com.spring.crowdfunding.navsam.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "parameters")
public class Parameters implements Serializable {

	private static final long serialVersionUID = 1506398396162017730L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "goal")
	private double goal;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	public Parameters(final LocalDate endDate, final double goal, final Timestamp timestamp) {
		this.endDate = endDate;
		this.goal = goal;
		this.timestamp = timestamp;
	}

}
