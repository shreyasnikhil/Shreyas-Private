package com.cognizant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.entities.IncidentTypes;
import com.cognizant.repositories.TypeRepository;

@DataJpaTest
@ContextConfiguration(classes = RideSharingPlatformApplication.class)
class TypeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TypeRepository typeRepository;

	@Test
	void testFindAllType_Positive() {
		IncidentTypes incidentType = new IncidentTypes();

		incidentType.setId(10);
		incidentType.setType(2);
		incidentType.setExpectedSLAInDay(4);

		entityManager.persist(incidentType);

		List<IncidentTypes> incidentTypeList = typeRepository.findAll();
		assertTrue(incidentTypeList.size() > 0);
	}

	@Test
	public void testSavePositive() {
		IncidentTypes incidentType = new IncidentTypes();

		incidentType.setId(10);
		incidentType.setType(2);
		incidentType.setExpectedSLAInDay(4);

		entityManager.persist(incidentType);
//	entityManager.persist(reimbursementRequests);
		Optional<IncidentTypes> request = typeRepository.findById(10);
		assertTrue(request.isPresent());
	}

	@Test
	public void testFindAllPositive() {
		IncidentTypes incidentType = new IncidentTypes();

		incidentType.setId(10);
		incidentType.setType(2);
		incidentType.setExpectedSLAInDay(4);

		entityManager.persist(incidentType);

		Iterable<IncidentTypes> it = typeRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}

	@Test
	public void testFindAllNegaitive() {
		Iterable<IncidentTypes> it = typeRepository.findAll();
		assertFalse(it.iterator().hasNext());
	}

	@Test
	public void testFindByIdPositive() {
		IncidentTypes incidentType = new IncidentTypes();

		incidentType.setId(10);
		incidentType.setType(2);
		incidentType.setExpectedSLAInDay(4);

		entityManager.persist(incidentType);

		Optional<IncidentTypes> request = typeRepository.findById(10);
		assertTrue(request.isPresent());
	}

	@Test
	public void testFindByIdNegaitive() {
		Optional<IncidentTypes> request = typeRepository.findById(7);
		assertFalse(request.isPresent());
	}

	@Test
	public void testSaveNegative() {
		Iterable<IncidentTypes> it = typeRepository.findAll();
		assertFalse(it.iterator().hasNext());
	}
}
