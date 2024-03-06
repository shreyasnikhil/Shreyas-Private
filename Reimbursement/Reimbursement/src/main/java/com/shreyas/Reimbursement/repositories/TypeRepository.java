package com.shreyas.Reimbursement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shreyas.Reimbursement.entities.ReimbursementTypes;

public interface TypeRepository extends JpaRepository<ReimbursementTypes, Integer> {
}
