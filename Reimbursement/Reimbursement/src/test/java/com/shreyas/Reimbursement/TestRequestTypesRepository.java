package com.shreyas.Reimbursement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.shreyas.Reimbursement.entities.ReimbursementTypes;
import com.shreyas.Reimbursement.repositories.TypeRepository;

class TestRequestTypesRepository {

	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testFindAllTypesPositive() {
		ReimbursementTypes reimbursementTypes=new ReimbursementTypes();
		reimbursementTypes.setId(3);
		reimbursementTypes.setType("Travel");
		entityManager.persist(reimbursementTypes);
		Iterable<ReimbursementTypes>it=typeRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	

}
