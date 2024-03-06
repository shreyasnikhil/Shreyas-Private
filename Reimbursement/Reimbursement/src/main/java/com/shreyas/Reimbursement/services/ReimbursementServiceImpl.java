package com.shreyas.Reimbursement.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.Reimbursement.entities.ReimbursementRequests;
import com.shreyas.Reimbursement.entities.ReimbursementTypes;
import com.shreyas.Reimbursement.models.AddNewReimbursementDTO;
import com.shreyas.Reimbursement.models.ProcessReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementTypesDTO;
import com.shreyas.Reimbursement.repositories.RequestRepository;
import com.shreyas.Reimbursement.repositories.TypeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReimbursementServiceImpl implements ReimbursementService {

	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private TypeRepository typeRepository;

	@Override
	public List<ReimbursementTypesDTO> getAvailableReimbursementTypes() {
		// TODO Auto-generated method stub
		List<ReimbursementTypes> reimbursementTypes = typeRepository.findAll();
		List<ReimbursementTypesDTO> dtos = new ArrayList<>();
		for (ReimbursementTypes types : reimbursementTypes) {
			ReimbursementTypesDTO dto = new ReimbursementTypesDTO();
			dto.setId(types.getId());
			dto.setType(types.getType());
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			return null;
		}
		return dtos;
	}

	@Override
	public String addReimbursement(AddNewReimbursementDTO addNewReimbursementDTO) {

		if (validateRecord(addNewReimbursementDTO)) {
			ReimbursementRequests reimbursementRequests = new ReimbursementRequests();
			reimbursementRequests.setTravelrequestid(addNewReimbursementDTO.getTravelrequestid());
			reimbursementRequests.setRequestRaisedByEmployeeId(addNewReimbursementDTO.getRequestRaisedByEmployeeId());
			reimbursementRequests.setRequestDate(addNewReimbursementDTO.getRequestDate());
			ReimbursementTypes reimbursementTypes = new ReimbursementTypes();
			reimbursementTypes.setId(addNewReimbursementDTO.getReimbursementTypesId());
			reimbursementRequests.setReimbursementTypes(reimbursementTypes);
			reimbursementRequests.setInvoiceNo(addNewReimbursementDTO.getInvoiceNo());
			reimbursementRequests.setInvoiceDate(addNewReimbursementDTO.getInvoiceDate());
			reimbursementRequests.setInvoiceAmount(addNewReimbursementDTO.getInvoiceAmount());
			reimbursementRequests.setDocumentUrl(addNewReimbursementDTO.getDocumentUrl());
			reimbursementRequests.setStatus(addNewReimbursementDTO.getStatus());
			if (requestRepository.save(reimbursementRequests) != null) {
				return "success";
			}
		}

		return null;
	}

	@Override
	public List<ReimbursementDTO> getAllReimbursentsByTravelRequestId(int id) {
		// TODO Auto-generated method stub
		List<ReimbursementRequests> reimbursementRequestsList = requestRepository.findByTravelrequestid(id);
		List<ReimbursementDTO> dtos = new ArrayList<>();
		for (ReimbursementRequests request : reimbursementRequestsList) {
			ReimbursementDTO dto = new ReimbursementDTO();
			dto.setId(request.getId());
			dto.setDocumentUrl(request.getDocumentUrl());
			dto.setInvoiceAmount(request.getInvoiceAmount());
			dto.setInvoiceDate(request.getInvoiceDate());
			dto.setInvoiceNo(request.getInvoiceNo());
			dto.setReimbursementTypesId(request.getReimbursementTypes().getId());
			dto.setRemarks(request.getRemarks());
			dto.setRequestDate(request.getRequestDate());
			dto.setRequestProcessedByEmployeeId(request.getRequestProcessedByEmployeeId());
			dto.setRequestProcessedOn(request.getRequestProcessedOn());
			dto.setRequestRaisedByEmployeeId(request.getRequestRaisedByEmployeeId());
			dto.setStatus(request.getStatus());
			dto.setTravelrequestid(request.getTravelrequestid());
			dtos.add(dto);

		}
		if (dtos.isEmpty()) {
			return null;
		}
		return dtos;
	}

	@Override
	public ReimbursementDTO getReimbursementById(int id) {
		// TODO Auto-generated method stub
		ReimbursementRequests request = requestRepository.findById(id).get();
		ReimbursementDTO dto = new ReimbursementDTO();
		dto.setId(request.getId());
		dto.setDocumentUrl(request.getDocumentUrl());
		dto.setInvoiceAmount(request.getInvoiceAmount());
		dto.setInvoiceDate(request.getInvoiceDate());
		dto.setInvoiceNo(request.getInvoiceNo());
		dto.setReimbursementTypesId(request.getReimbursementTypes().getId());
		dto.setRemarks(request.getRemarks());
		dto.setRequestDate(request.getRequestDate());
		dto.setRequestProcessedByEmployeeId(request.getRequestProcessedByEmployeeId());
		dto.setRequestProcessedOn(request.getRequestProcessedOn());
		dto.setRequestRaisedByEmployeeId(request.getRequestRaisedByEmployeeId());
		dto.setStatus(request.getStatus());
		dto.setTravelrequestid(request.getTravelrequestid());
		return dto;
	}

	@Override
	public String processReimbursementRequests(ProcessReimbursementDTO dto) {
		Optional<ReimbursementRequests> existingReimbursementOptional = requestRepository
				.findById(dto.getReimbursementId());
		ReimbursementRequests updated = null;
		if (existingReimbursementOptional.isPresent()) {
			ReimbursementRequests reimbursementRequests = existingReimbursementOptional.get();
			reimbursementRequests.setRequestProcessedOn(dto.getRequestProcessedOn());
			reimbursementRequests.setRequestProcessedByEmployeeId(dto.getRequestProcessedByEmployeeId());
			reimbursementRequests.setStatus(dto.getStatus());
			reimbursementRequests.setRemarks(dto.getRemarks());
			if (dto.getStatus().equals("Rejected")) {
				if (dto.getRemarks() != null) {
					reimbursementRequests.setRemarks(dto.getRemarks());
				} else {
					return "Remarks cannot be null please re-enter the data";
				}
			}
			updated = requestRepository.save(reimbursementRequests);
			if (updated != null) {
				return "success";
			}
		}
		return "fail";
	}

	public boolean validateRecord(AddNewReimbursementDTO addNewReimbursementDTO) {
		String pdfFilePath = addNewReimbursementDTO.getDocumentUrl();

		try {
			File pdfFile = new File(pdfFilePath);
			long fileSizeInBytes = pdfFile.length();
			long fileSizeInKB = fileSizeInBytes / 1024;
			if (fileSizeInKB > 256) {
				return false;
			}
			Optional<ReimbursementTypes> optionalTypes = typeRepository
					.findById(addNewReimbursementDTO.getReimbursementTypesId());
			String type = optionalTypes.get().getType();
			if (type.equalsIgnoreCase("Food and Water")) {
				int amount = addNewReimbursementDTO.getInvoiceAmount();
				if (amount >= 1000 && amount <= 1500) {
					return true;
				}
			} else if (type.equals("Travel")) {
				int amount = addNewReimbursementDTO.getInvoiceAmount();
				if (amount <= 1000) {
					return true;
				}
			} else if (type.equals("Laundry")) {
				int amount = addNewReimbursementDTO.getInvoiceAmount();
				if (amount >= 1000 && amount <= 1500) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
