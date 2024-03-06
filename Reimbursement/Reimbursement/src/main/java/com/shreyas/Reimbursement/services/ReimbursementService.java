package com.shreyas.Reimbursement.services;

import java.util.List;

import com.shreyas.Reimbursement.models.AddNewReimbursementDTO;
import com.shreyas.Reimbursement.models.ProcessReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementTypesDTO;

public interface ReimbursementService {
	public List<ReimbursementTypesDTO> getAvailableReimbursementTypes();
	public String addReimbursement(AddNewReimbursementDTO addNewReimbursementDTO);
	public List<ReimbursementDTO> getAllReimbursentsByTravelRequestId(int id);
	public ReimbursementDTO getReimbursementById(int id);
	public String processReimbursementRequests(ProcessReimbursementDTO dto);
}

