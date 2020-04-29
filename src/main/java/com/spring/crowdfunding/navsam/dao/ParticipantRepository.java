
package com.spring.crowdfunding.navsam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

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
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

}
