
package com.spring.crowdfunding.navsam.entity.listner;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     20 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
public class PreventAnyUpdate {
	@PrePersist
	void onPrePersist(final Object o) {
		throw new RuntimeException("Could not save this entity");
	}

	@PreUpdate
	void onPreUpdate(final Object o) {
		throw new RuntimeException("Could not update this entity");
	}

	@PreRemove
	void onPreRemove(final Object o) {
		throw new RuntimeException("Could not remove this entity");
	}
}
