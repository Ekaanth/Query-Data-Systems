package com.qds.sa.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.RequestAccess;
import com.qds.sa.util.constant.ActiveStatus;

@Repository
public interface RequestAccessRep extends JpaRepository<RequestAccess, Long> {

	public List<RequestAccess> findByUrequeststatus(ActiveStatus userStatus);

	public RequestAccess findByUid(String id);
}
