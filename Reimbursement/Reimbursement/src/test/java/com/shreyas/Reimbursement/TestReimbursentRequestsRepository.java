package com.shreyas.Reimbursement;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.shreyas.Reimbursement.entities.ReimbursementRequests;
import com.shreyas.Reimbursement.entities.ReimbursementTypes;
import com.shreyas.Reimbursement.repositories.RequestRepository;
import com.shreyas.Reimbursement.repositories.TypeRepository;
@DataJpaTest
@ContextConfiguration(classes=ReimbursementApplication.class)
class TestReimbursentRequestsRepository {

	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private TypeRepository typeRepository; 
	
	@Test
	public void testSavePositive() {
		ReimbursementTypes reimbursementTypes=new ReimbursementTypes();
		reimbursementTypes.setId(3);
		reimbursementTypes.setType("Laundry");
//		entityManager.persist(reimbursementTypes);
		typeRepository.save(reimbursementTypes);
		ReimbursementRequests reimbursementRequests=new ReimbursementRequests();
		//reimbursementRequests.setId(5);
		reimbursementRequests.setTravelrequestid(1001);
		reimbursementRequests.setReimbursementTypes(reimbursementTypes);
		reimbursementRequests.setDocumentUrl("C:\\Users\\2317239\\Documents");
		reimbursementRequests.setRequestDate(LocalDate.now());
//		entityManager.persist(reimbursementRequests);
		requestRepository.save(reimbursementRequests);
		Optional<ReimbursementRequests> request=requestRepository.findById(1);
		assertTrue(request.isPresent());
	}
	@Test
	public void testFindAllPositive() {
		ReimbursementTypes reimbursementTypes=new ReimbursementTypes();
		reimbursementTypes.setId(3);
		reimbursementTypes.setType("Laundry");
		entityManager.persist(reimbursementTypes);
		ReimbursementRequests reimbursementRequests=new ReimbursementRequests();
		//reimbursementRequests.setId(5);
		reimbursementRequests.setTravelrequestid(1001);
		reimbursementRequests.setReimbursementTypes(reimbursementTypes);
		reimbursementRequests.setDocumentUrl("C:\\Users\\2317239\\Documents");
		reimbursementRequests.setRequestDate(LocalDate.now());
		entityManager.persist(reimbursementRequests);
		Iterable<ReimbursementRequests>it=requestRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindAllNegaitive() {
		Iterable<ReimbursementRequests>it=requestRepository.findAll();
		assertFalse(it.iterator().hasNext());
	}
	@Test
	public void testFindByIdPositive() {
		ReimbursementTypes reimbursementTypes=new ReimbursementTypes();
		reimbursementTypes.setId(3);
		reimbursementTypes.setType("Laundry");
		entityManager.persist(reimbursementTypes);
		ReimbursementRequests reimbursementRequests=new ReimbursementRequests();
		reimbursementRequests.setId(2);
		reimbursementRequests.setTravelrequestid(1001);
		reimbursementRequests.setReimbursementTypes(reimbursementTypes);
		reimbursementRequests.setDocumentUrl("C:\\Users\\2317239\\Documents");
		reimbursementRequests.setRequestDate(LocalDate.now());
		entityManager.persist(reimbursementRequests);
		Optional<ReimbursementRequests> request=requestRepository.findById(2);
		assertTrue(request.isPresent());
	}
	@Test
	public void testFindByIdNegaitive() {
		Optional<ReimbursementRequests> request=requestRepository.findById(7);
		assertFalse(request.isPresent());
	}
	@Test
	public void testFindByTravelRequestIdPositive() {
		ReimbursementTypes reimbursementTypes=new ReimbursementTypes();
		reimbursementTypes.setId(3);
		reimbursementTypes.setType("Laundry");
		entityManager.persist(reimbursementTypes);
		ReimbursementRequests reimbursementRequests=new ReimbursementRequests();
		//reimbursementRequests.setId(5);
		reimbursementRequests.setTravelrequestid(1001);
		reimbursementRequests.setReimbursementTypes(reimbursementTypes);
		reimbursementRequests.setDocumentUrl("C:\\Users\\2317239\\Documents");
		reimbursementRequests.setRequestDate(LocalDate.now());
		entityManager.persist(reimbursementRequests);
		Iterable<ReimbursementRequests> it=requestRepository.findByTravelrequestid(1001);
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindByTravelRequestIdNegative() {
		Iterable<ReimbursementRequests> it=requestRepository.findByTravelrequestid(1001);
		assertFalse(it.iterator().hasNext());
	}
	@Test
	public void testSaveNegative() {
		Iterable<ReimbursementRequests> it=requestRepository.findAll();
		assertFalse(it.iterator().hasNext());
	}

}
