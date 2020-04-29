
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Participant;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     26 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
public interface ParticipantService {

	public List<Participant> findAll();

	public Participant findById(int theId);

	public void save(Participant theParticipant);

	public void deleteById(int theId);

}
