package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.UserProfile;

@Repository
public interface UserProfileRep extends JpaRepository<UserProfile, Long> {

	public UserProfile findByUqueryid(String id);
}
