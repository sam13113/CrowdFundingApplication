
package com.spring.crowdfunding.navsam.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crowdfunding.navsam.dao.ParticipantRepository;
import com.spring.crowdfunding.navsam.entity.Participant;
import com.spring.crowdfunding.navsam.service.ParticipantService;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

	private ParticipantRepository participantRepository;

	@Override
	public List<Participant> findAll() {
		List<Participant> participantList = getParticipantRepository().findAll();
		return participantList;
	}

	@Override
	public Participant findById(final int theId) {
		Optional<Participant> participant = getParticipantRepository().findById(theId);

		if (participant.isPresent()) {
			return participant.get();
		} else {
			throw new RuntimeException("Did not find Participant id - " + theId);
		}
	}

	@Override
	public void save(final Participant theParticipant) {

		getParticipantRepository().save(theParticipant);

	}

	@Override
	public void deleteById(final int theId) {
		getParticipantRepository().deleteById(theId);

	}

	@Autowired
	public void setParticipantRepository(final ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

}
