package com.cognizant.repositories.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.RideSharingPlatformApplication;
import com.cognizant.entities.InvestingationDetails;
import com.cognizant.repositories.InvestigationRepository;
import com.cognizant.repositories.TypeRepository;
@DataJpaTest
@ContextConfiguration(classes = RideSharingPlatformApplication.class)
class InvestigationRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private InvestigationRepository investigationRepository;

	@Test
	public void testSaveInvestigationDetails() {
		InvestingationDetails investigation = new InvestingationDetails();
		investigation.setFindings("Sample Investigation");

		InvestingationDetails savedInvestigation = investigationRepository.save(investigation);

		assertNotNull(savedInvestigation.getId());
		//assertEquals("Sample Investigation", savedInvestigation.getFindings());
	}

	@Test
	public void testFindInvestigationById() {
		InvestingationDetails investigation = new InvestingationDetails();
		investigation.setFindings("Sample Investigation");
		investigation.setId(1);
		InvestingationDetails savedInvestigation = entityManager.persistAndFlush(investigation);

		Optional<InvestingationDetails> foundInvestigationOptional = investigationRepository
				.findById(savedInvestigation.getId());
		InvestingationDetails foundInvestigation = foundInvestigationOptional.orElse(null);

		assertNotNull(foundInvestigation);
		//assertEquals(savedInvestigation.getId(), foundInvestigation.getId());
		//assertEquals(savedInvestigation.getFindings(), foundInvestigation.getFindings());
	}

	@Test
	public void testFindAllInvestigations() {
		InvestingationDetails investigation1 = new InvestingationDetails();
		investigation1.setFindings("Sample Investigation 1");
		investigation1.setId(1);
		InvestingationDetails investigation2 = new InvestingationDetails();
		investigation2.setFindings("Sample Investigation 2");
		investigation2.setId(2);
		entityManager.persistAndFlush(investigation1);
		entityManager.persistAndFlush(investigation2);

		List<InvestingationDetails> allInvestigations = investigationRepository.findAll();

		assertEquals(2, allInvestigations.size());
	}

}
