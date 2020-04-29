
package com.spring.crowdfunding.navsam.controller.vm;

import com.spring.crowdfunding.navsam.entity.ProjectRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@ToString
public class ProjectTeamVM {

	private int id;

	private int projectId;

	private ProjectRole projectRole;

	private int participantId;

	private String participantResponsibilities;

}
