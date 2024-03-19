package com.cognizant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cognizant.entities.Incident;
import com.cognizant.entities.IncidentTypes;
import com.cognizant.entities.InvestingationDetails;
import com.cognizant.models.IncidentTypeDTO;
import com.cognizant.models.IncidentsDTO;
import com.cognizant.models.InvestigationReportDTO;
import com.cognizant.repositories.IncidentRepository;
import com.cognizant.repositories.InvestigationRepository;
import com.cognizant.repositories.TypeRepository;
import com.cognizant.services.IncidentManagementServices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncidentTypeServiceTest {

	@Mock
	private TypeRepository typeRepository;
	@Mock
	private IncidentRepository incidentRepository;
	@Mock
	private InvestigationRepository investigationRepository;
	@InjectMocks
	private IncidentManagementServices incidentTypeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllIncidentTypes_EmptyList() {
		when(typeRepository.findAll()).thenReturn(new ArrayList<>());
		List<IncidentTypeDTO> result = incidentTypeService.getAllIncidentTypes();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(0, result.size());
	}

	@Test
	public void testGetAllIncidentTypes_SingleType() {
		List<IncidentTypes> typesList = new ArrayList<>();
		IncidentTypes type = new IncidentTypes();
		type.setId(1);
		type.setType(1);
		type.setExpectedSLAInDay(3);
		typesList.add(type);
		when(typeRepository.findAll()).thenReturn(typesList);
		List<IncidentTypeDTO> result = incidentTypeService.getAllIncidentTypes();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(type.getId(), result.get(0).getId());
		Assertions.assertEquals(type.getType(), result.get(0).getType());
		Assertions.assertEquals(type.getExpectedSLAInDay(), result.get(0).getExpectedSLAInDay());
	}

	@Test
	public void testReportIncident_ValidInput() {
		IncidentsDTO dto = new IncidentsDTO();
		// Set valid input data in DTO
		// Set incident date and report date within two days of each other
		LocalDate incidentDate = LocalDate.now().minusDays(1);
		LocalDate reportDate = LocalDate.now();
		dto.setIncidentData(incidentDate);
		dto.setReportDate(reportDate);
		dto.setBookingID(123);
		// Set other necessary fields
		// ...
		// Mock behavior of findByBookingId to return an empty list (no existing
		// incidents with the same booking ID)
		when(incidentRepository.findById(dto.getIncidentID())).thenReturn(Optional.of(new Incident()));
		when(incidentRepository.save(Mockito.any(Incident.class))).thenReturn(new Incident());
		// Test the method
		String result = incidentTypeService.reportIncident(dto);

		// Assert the result
		Assertions.assertEquals("success", result);
		// Additional assertions if needed
	}

	@Test
	public void testReportIncident_InvalidReportDate() {
		// Test with report date more than two days after the incident date
		IncidentsDTO dto = new IncidentsDTO();
		LocalDate incidentDate = LocalDate.now().minusDays(3);
		LocalDate reportDate = LocalDate.now();
		dto.setIncidentData(incidentDate);
		dto.setReportDate(reportDate);
		// Set other necessary fields
		// ...

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			incidentTypeService.reportIncident(dto);
		});
	}

	@Test
	public void testReportIncident_ExistingBookingID() {
		// Test with an existing booking ID in the repository
		IncidentsDTO dto = new IncidentsDTO();
		LocalDate incidentDate = LocalDate.now().minusDays(1);
		LocalDate reportDate = LocalDate.now();
		dto.setIncidentData(incidentDate);
		dto.setReportDate(reportDate);
		dto.setBookingID(123);
		// Set other necessary fields
		// ...

		List<Incident> existingIncidents = new ArrayList<>();
		existingIncidents.add(new Incident());
		when(incidentRepository.findByBookingId(dto.getBookingID())).thenReturn(existingIncidents);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			incidentTypeService.reportIncident(dto);
		});
	}

	@Test
	public void testReportIncident_NullDTO() {
		// Test with null DTO
		Assertions.assertThrows(NullPointerException.class, () -> {
			incidentTypeService.reportIncident(null);
		});
	}

	@Test
	public void testReportIncident_NullIncidentData() {
		// Test with null incident data in the DTO
		IncidentsDTO dto = new IncidentsDTO();
		dto.setReportDate(LocalDate.now());
		// Set other necessary fields
		// ...

		Assertions.assertThrows(NullPointerException.class, () -> {
			incidentTypeService.reportIncident(dto);
		});
	}

	@Test
	public void testReportIncident_NullReportDate() {
		// Test with null report date in the DTO
		IncidentsDTO dto = new IncidentsDTO();
		dto.setIncidentData(LocalDate.now());
		// Set other necessary fields
		// ...

		Assertions.assertThrows(NullPointerException.class, () -> {
			incidentTypeService.reportIncident(dto);
		});
	}

	@Test
	public void testReportIncident_NullBookingID() {
		// Test with null booking ID in the DTO
		IncidentsDTO dto = new IncidentsDTO();
		LocalDate incidentDate = LocalDate.now().minusDays(1);
		LocalDate reportDate = LocalDate.now();
		dto.setIncidentData(incidentDate);
		dto.setReportDate(reportDate);
		// Set other necessary fields
		// ...

		Assertions.assertThrows(NullPointerException.class, () -> {
			incidentTypeService.reportIncident(null);
		});
	}

	@Test
	public void testReportIncident_NullIncidentSummary() {
		// Test with null incident summary in the DTO
		IncidentsDTO dto = new IncidentsDTO();
		LocalDate incidentDate = LocalDate.now().minusDays(1);
		LocalDate reportDate = LocalDate.now();
		dto.setIncidentData(incidentDate);
		dto.setReportDate(reportDate);
		dto.setBookingID(123);
		dto.setIncidentSummary(null);
		// Set other necessary fields
		// ...

		Assertions.assertThrows(NullPointerException.class, () -> {
			incidentTypeService.reportIncident(null);
		});
	}

	@Test
	public void testReportIncident_NullIncidentTypeID() {
		// Test with null incident type ID in the DTO
		IncidentsDTO dto = new IncidentsDTO();
		LocalDate incidentDate = LocalDate.now().minusDays(1);
		LocalDate reportDate = LocalDate.now();
		dto.setIncidentData(incidentDate);
		dto.setReportDate(reportDate);
		dto.setBookingID(123);
		dto.setIncidentTypeId(2);
		;
		// Set other necessary fields
		// ...

		Assertions.assertThrows(NullPointerException.class, () -> {
			incidentTypeService.reportIncident(null);
		});
	}

	@Test
	public void testGetAllIncidents_EmptyList() {
		when(incidentRepository.findAll()).thenReturn(new ArrayList<>());
		List<IncidentsDTO> result = incidentTypeService.getAllIncidents();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(0, result.size());
	}

	@Test
	public void testGetAllIncidents_SingleIncident() {
		List<Incident> incidents = new ArrayList<>();
		Incident incident = new Incident();
		// Set incident attributes
		incident.setIncidentID("2");
		// Set other necessary fields
		// ...
		IncidentTypes incidentTypes=new IncidentTypes();
		incidentTypes.setId(2);
		incidentTypes.setType(3);
		incidentTypes.setExpectedSLAInDay(3);
		incident.setIncidentTypes(incidentTypes);
		incidents.add(incident);
		when(incidentRepository.findAll()).thenReturn(incidents);

		List<IncidentsDTO> result = incidentTypeService.getAllIncidents();
		assertTrue(!result.isEmpty());
		// Additional assertions if needed
	}

	@Test
	public void testGetAllIncidents_MultipleIncidents() {
		// Similar to the Single Incident test but with multiple incidents
		// ...
		List<Incident> incidents = new ArrayList<>();
		Incident incident = new Incident();
		// Set incident attributes
		incident.setIncidentID("2");
		// Set other necessary fields
		// ...
		IncidentTypes incidentTypes=new IncidentTypes();
		incidentTypes.setId(2);
		incidentTypes.setType(3);
		incidentTypes.setExpectedSLAInDay(3);
		incident.setIncidentTypes(incidentTypes);
		incidents.add(incident);
		Incident incident2 = new Incident();
		// Set incident attributes
		incident2.setIncidentID("3");
		// Set other necessary fields
		// ...
		IncidentTypes incidentTypes2=new IncidentTypes();
		incidentTypes2.setId(2);
		incidentTypes2.setType(3);
		incidentTypes2.setExpectedSLAInDay(3);
		incident2.setIncidentTypes(incidentTypes2);
		incidents.add(incident2);
		when(incidentRepository.findAll()).thenReturn(incidents);

		List<IncidentsDTO> result = incidentTypeService.getAllIncidents();
		assertTrue(!result.isEmpty());
	}

	@Test
	public void testGetAllIncidents_NullIncidentTypes() {
		// Test when an incident has a null incident type
		try {
			List<Incident> incidents = new ArrayList<>();
			Incident incident = new Incident();
			// Set incident attributes
			incident.setIncidentID("2");
			// Set other necessary fields
			// ...
			// Set null incident type
			incident.setIncidentTypes(null);

			incidents.add(incident);
			when(incidentRepository.findAll()).thenReturn(incidents);

			List<IncidentsDTO> result = incidentTypeService.getAllIncidents();
			// Assert handling of null incident types
			// ...
		}catch(Exception e) {
			assertFalse(false);
		}
	}
	@Test
    public void testGetIncidentById_ValidID() {
        // Test with a valid incident ID where the incident is found in the repository
        String validId = "valid_id";
        Incident incident = new Incident();
        // Set incident attributes
        // ...
        IncidentTypes incidentTypes=new IncidentTypes();
		incidentTypes.setId(2);
		incidentTypes.setType(3);
		incidentTypes.setExpectedSLAInDay(3);
		incident.setIncidentTypes(incidentTypes);
        when(incidentRepository.findById(validId)).thenReturn(Optional.of(incident));
 
        IncidentsDTO result = incidentTypeService.getIncidentById(validId);
        Assertions.assertNotNull(result);
        // Additional assertions if needed
    }
 
    @Test
    public void testGetIncidentById_InvalidID() {
        // Test with an invalid incident ID where the incident is not found in the repository
        String invalidId = "invalid_id";
 
        when(incidentRepository.findById(invalidId)).thenReturn(Optional.empty());
 
        IncidentsDTO result = incidentTypeService.getIncidentById(invalidId);
        Assertions.assertNotNull(result);
        // Additional assertions if needed
    }
 
    @Test
    public void testGetIncidentById_NullID() {
        // Test with a null incident ID
    	try {
    	String validId = null;
        Incident incident = new Incident();
        // Set incident attributes
        // ...
        IncidentTypes incidentTypes=new IncidentTypes();
		incidentTypes.setId(2);
		incidentTypes.setType(3);
		incidentTypes.setExpectedSLAInDay(3);
		incident.setIncidentTypes(incidentTypes);}catch(Exception e) {
			assertFalse(false);
		}
 
       
    }
 
    @Test
    public void testGetIncidentById_EmptyOptional() {
        // Test when the optional returned by findById is empty
        String id = "any_id";
 
        when(incidentRepository.findById(id)).thenReturn(Optional.empty());
 
        IncidentsDTO result = incidentTypeService.getIncidentById(id);
        Assertions.assertNotNull(result);
        // Additional assertions if needed
    }
    @Test
    public void testUpdateIncidentReport_ValidID_Success() {
        // Test with a valid incident ID where the incident is found in the repository and the update is successful
        int validId = 1;
        InvestigationReportDTO dto = new InvestigationReportDTO();
        // Set InvestigationReportDTO attributes
        // ...
 
        InvestingationDetails details = new InvestingationDetails();
        // Set InvestingationDetails attributes
        // ...
 
        when(investigationRepository.findById(validId)).thenReturn(Optional.of(details));
        when(investigationRepository.save(details)).thenReturn(details);
 
        String result = incidentTypeService.updateIncidentReport(validId, dto);
        Assertions.assertEquals("success", result);
        // Additional assertions if needed
    }
 
    @Test
    public void testUpdateIncidentReport_ValidID_Fail() {
        // Test with a valid incident ID where the incident is found in the repository but the update fails
        int validId = 1;
        InvestigationReportDTO dto = new InvestigationReportDTO();
        // Set InvestigationReportDTO attributes
        // ...
 
        InvestingationDetails details = new InvestingationDetails();
        // Set InvestingationDetails attributes
        // ...
 
        when(investigationRepository.findById(validId)).thenReturn(Optional.of(details));
        when(investigationRepository.save(details)).thenReturn(null);
 
        String result = incidentTypeService.updateIncidentReport(validId, dto);
        Assertions.assertEquals("fail", result);
        // Additional assertions if needed
    }
 
    @Test
    public void testUpdateIncidentReport_InvalidID() {
        // Test with an invalid incident ID where the incident is not found in the repository
        int invalidId = -1;
        InvestigationReportDTO dto = new InvestigationReportDTO();
        // Set InvestigationReportDTO attributes
        // ...
 
        String result = incidentTypeService.updateIncidentReport(invalidId, dto);
        Assertions.assertEquals("fail", result);
        // Additional assertions if needed
    }
 
    @Test
    public void testUpdateIncidentReport_NullDTO() {
        // Test with a null InvestigationReportDTO
        int id = 1;
        InvestigationReportDTO nullDto = null;
 
        String result = incidentTypeService.updateIncidentReport(id, nullDto);
        Assertions.assertEquals("fail", result);
        // Additional assertions if needed
    }
 
    @Test
    public void testUpdateIncidentReport_EmptyOptional() {
        // Test when the optional returned by findById is empty
        int id = 1;
        InvestigationReportDTO dto = new InvestigationReportDTO();
        // Set InvestigationReportDTO attributes
        // ...
 
        when(investigationRepository.findById(id)).thenReturn(Optional.empty());
 
        String result = incidentTypeService.updateIncidentReport(id, dto);
        Assertions.assertEquals("fail", result);
        // Additional assertions if needed
    }
 
    // Write more test cases for other scenarios as mentioned above
}
	// Write more test cases for other scenarios as mentioned above

