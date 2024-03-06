package com.shreyas.Reimbursement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shreyas.Reimbursement.entities.ReimbursementRequests;

public interface RequestRepository extends JpaRepository<ReimbursementRequests, Integer> {
	//List<ReimbursementRequests>findByTravelRequestId(Integer travelrequestid);
	List<ReimbursementRequests> findByTravelrequestid(int travelrequestid);
}
