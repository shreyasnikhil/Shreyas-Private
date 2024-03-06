package com.shreyas.Reimbursement;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.shreyas.Reimbursement.entities.ReimbursementRequests;
import com.shreyas.Reimbursement.entities.ReimbursementTypes;
import com.shreyas.Reimbursement.models.AddNewReimbursementDTO;
import com.shreyas.Reimbursement.models.ProcessReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementTypesDTO;
import com.shreyas.Reimbursement.repositories.RequestRepository;
import com.shreyas.Reimbursement.repositories.TypeRepository;
import com.shreyas.Reimbursement.services.ReimbursementServiceImpl;

@SpringBootTest
//@ContextConfiguration(classes = ReimbursementApplication.class)
class TestReimbursementServiceImpl {
	@Mock
	private RequestRepository requestRepository;

	@Mock
	private TypeRepository typeRepository;
	@InjectMocks
	ReimbursementServiceImpl reimbursementServices;

	@Test
	void testGetAvailableReimbursementTypes() {
		ReimbursementTypes reimbursementTypes = new ReimbursementTypes();
		reimbursementTypes.setId(1);
		reimbursementTypes.setType("Food");
		ReimbursementTypes reimbursementTypes1 = new ReimbursementTypes();
		reimbursementTypes1.setId(2);
		reimbursementTypes1.setType("Water");
		List<ReimbursementTypes> mockTypes = Arrays.asList(reimbursementTypes, reimbursementTypes1
		// Add more mock data as needed
		);

		// Mock repository behavior
		Mockito.when(typeRepository.findAll()).thenReturn(mockTypes);

		// Call the service method
		List<ReimbursementTypesDTO> dtos = reimbursementServices.getAvailableReimbursementTypes();

		// Verify the result
		assertTrue(!dtos.isEmpty());
	}

	@Test
	void testAddReimbursement() {
		// Create a sample AddNewReimbursementDTO with a small document size
		AddNewReimbursementDTO mockDTO = new AddNewReimbursementDTO();
		mockDTO.setTravelrequestid(1001);
		mockDTO.setDocumentUrl("C:\\Users\\2317239\\OneDrive - Cognizant\\Documents\\Restaurant_Sample_Invoice.pdf");
		mockDTO.setInvoiceAmount(1200);
		mockDTO.setInvoiceDate(LocalDate.parse("2028-02-29"));
		mockDTO.setInvoiceNo("INV001");
		mockDTO.setReimbursementTypesId(2);
		mockDTO.setRequestRaisedByEmployeeId(1001);
		ReimbursementRequests reimbursementRequests = new ReimbursementRequests();
		reimbursementRequests.setTravelrequestid(mockDTO.getTravelrequestid());
		reimbursementRequests.setRequestRaisedByEmployeeId(mockDTO.getRequestRaisedByEmployeeId());
		reimbursementRequests.setRequestDate(mockDTO.getRequestDate());
		ReimbursementTypes reimbursementTypes = new ReimbursementTypes();
		reimbursementTypes.setId(mockDTO.getReimbursementTypesId());
		reimbursementTypes.setType("Laundry");
		reimbursementRequests.setReimbursementTypes(reimbursementTypes);
		reimbursementRequests.setInvoiceNo(mockDTO.getInvoiceNo());
		reimbursementRequests.setInvoiceDate(mockDTO.getInvoiceDate());
		reimbursementRequests.setInvoiceAmount(mockDTO.getInvoiceAmount());
		reimbursementRequests.setDocumentUrl(mockDTO.getDocumentUrl());

		// Mocking behavior for the repository save method
		Mockito.when(typeRepository.findById(mockDTO.getReimbursementTypesId()))
		.thenReturn(Optional.of(reimbursementTypes));
		
		Mockito.when(requestRepository.save(Mockito.any(ReimbursementRequests.class)))
				.thenReturn(reimbursementRequests);

		// Call the method under test
		String result = reimbursementServices.addReimbursement(mockDTO);
		assertEquals("success", result);
	}

	@Test
	void testGetAllReimbursentsByTravelRequestId() {
		ReimbursementTypes reimbursementTypes = new ReimbursementTypes();
		reimbursementTypes.setId(3);
		reimbursementTypes.setType("Food and Water");
		ReimbursementRequests reimbursementRequests1 = new ReimbursementRequests();
		reimbursementRequests1.setTravelrequestid(1001);
		reimbursementRequests1.setReimbursementTypes(reimbursementTypes);
		ReimbursementRequests reimbursementRequests2 = new ReimbursementRequests();
		reimbursementRequests2.setTravelrequestid(1001);
		reimbursementRequests2.setReimbursementTypes(reimbursementTypes);
		ReimbursementDTO dto1 = new ReimbursementDTO();
		dto1.setTravelrequestid(reimbursementRequests1.getTravelrequestid());
		dto1.setReimbursementTypesId(reimbursementTypes.getId());
		ReimbursementDTO dto2 = new ReimbursementDTO();
		dto2.setTravelrequestid(reimbursementRequests2.getTravelrequestid());
		dto2.setReimbursementTypesId(reimbursementTypes.getId());

		List<ReimbursementRequests> mockRequests = Arrays.asList(reimbursementRequests1, reimbursementRequests2
		// Add more mock data as needed
		);

		// Mock repository behavior
		Mockito.when(requestRepository.findByTravelrequestid(1001)).thenReturn(mockRequests);

		// Call the service method
		List<ReimbursementDTO> result = reimbursementServices.getAllReimbursentsByTravelRequestId(1001);

		// Verify the result
		assertTrue(!result.isEmpty());
		// Add more assertions as needed
	}

	@Test
	void testGetReimbursementById() {
		ReimbursementRequests mockReimbursementRequest = new ReimbursementRequests();
		mockReimbursementRequest.setTravelrequestid(123);
		mockReimbursementRequest.setRequestRaisedByEmployeeId(456);
		mockReimbursementRequest.setId(1);
		mockReimbursementRequest.setInvoiceNo("INV123");
		ReimbursementTypes mockReimbursementTypes = new ReimbursementTypes();
		mockReimbursementTypes.setId(3);
		mockReimbursementRequest.setReimbursementTypes(mockReimbursementTypes);

		Mockito.when(requestRepository.findById(mockReimbursementRequest.getId()))
				.thenReturn(Optional.of(mockReimbursementRequest));

		// Call the service method
		ReimbursementDTO result = reimbursementServices.getReimbursementById(1);
		assertNotNull(result);
	}

	@Test
	void testProcessReimbursementRequests() {
		ProcessReimbursementDTO mockDTO = new ProcessReimbursementDTO();
		mockDTO.setReimbursementId(1);
		mockDTO.setRemarks("Approved");
		mockDTO.setRequestProcessedByEmployeeId(1002);
		mockDTO.setRequestProcessedOn(LocalDate.now());
		mockDTO.setRequestRaisedByEmployeeId(101);
		mockDTO.setStatus("Approved");
		mockDTO.setRequestRaisedByEmployeeId(1001);
		ReimbursementRequests reimbursementRequests = new ReimbursementRequests();
		reimbursementRequests.setId(mockDTO.getReimbursementId());
		reimbursementRequests.setRequestProcessedOn(mockDTO.getRequestProcessedOn());
		reimbursementRequests.setRequestProcessedByEmployeeId(mockDTO.getRequestProcessedByEmployeeId());
		reimbursementRequests.setStatus(mockDTO.getStatus());
		reimbursementRequests.setRemarks(mockDTO.getRemarks());
		Mockito.when(requestRepository.findById(reimbursementRequests.getId()))
				.thenReturn(Optional.of(reimbursementRequests));

		Mockito.when(requestRepository.save(Mockito.any(ReimbursementRequests.class)))
				.thenReturn(reimbursementRequests);
//		System.out.println(reimbursementRequests.getStatus());
		String result = reimbursementServices.processReimbursementRequests(mockDTO);

		// Assert that the result is not null (indicating success)
		assertNotNull(result);
		assertEquals("success", result);

	}

}
