
package com.spring.crowdfunding.navsam.service;

import java.util.List;

import com.spring.crowdfunding.navsam.entity.Comment;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     30 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
public interface CommentService {

	public List<Comment> findAll();

	public Comment findById(int theId);

	public void save(Comment theComment);

	public void deleteById(int theId);

}
