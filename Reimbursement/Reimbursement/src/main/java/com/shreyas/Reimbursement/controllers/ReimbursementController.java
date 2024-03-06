package com.shreyas.Reimbursement.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.Reimbursement.entities.ReimbursementRequests;
import com.shreyas.Reimbursement.entities.ReimbursementTypes;
import com.shreyas.Reimbursement.models.AddNewReimbursementDTO;
import com.shreyas.Reimbursement.models.ProcessReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementDTO;
import com.shreyas.Reimbursement.models.ReimbursementTypesDTO;
import com.shreyas.Reimbursement.repositories.RequestRepository;
import com.shreyas.Reimbursement.repositories.TypeRepository;
import com.shreyas.Reimbursement.services.ReimbursementServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api")
public class ReimbursementController {
	@Autowired 
	private ReimbursementServiceImpl reimbursementServiceImpl;
	//EndPoint 1
	@GetMapping("reimbursements/types")
	public ResponseEntity<?> getAllReimbursementTypes(){
		List<ReimbursementTypesDTO> responseList=reimbursementServiceImpl.getAvailableReimbursementTypes();
		ResponseEntity<List<ReimbursementTypesDTO>> responseEntity=null;
		if(!responseList.isEmpty()) {
			responseEntity=new ResponseEntity<List<ReimbursementTypesDTO>>(responseList,HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	//EndPoint 2
	@PostMapping("reimbursements/add")
	public ResponseEntity<?> addNewReimbursement(@Valid@RequestBody AddNewReimbursementDTO addNewReimbursementDTO){
		String result=reimbursementServiceImpl.addReimbursement(addNewReimbursementDTO);
		if(result.equals("success")) {
			return new ResponseEntity<>("Created",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Not Created",HttpStatus.BAD_REQUEST);
		}
		
	}
	//EndPoint 3
		@GetMapping("reimbursements/{travelrequestid}/requests")
		public ResponseEntity<?> getAllReimbursementByTravelRequestId(@PathVariable("travelrequestid") int travelRequestId){
			List<ReimbursementDTO> responseList=reimbursementServiceImpl.getAllReimbursentsByTravelRequestId(travelRequestId);
			ResponseEntity<List<ReimbursementDTO>> responseEntity=null;
			if(!responseList.isEmpty()) {
				responseEntity=new ResponseEntity<List<ReimbursementDTO>>(responseList,HttpStatus.OK);
			}else {
				responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
		}
		//EndPoint 4
		@GetMapping("reimbursements/{id}")
		public ResponseEntity<?> getReimbursementById(@PathVariable("id") int id){
			ReimbursementDTO reimbursementDTO=reimbursementServiceImpl.getReimbursementById(id);
			ResponseEntity<ReimbursementDTO> responseEntity=null;
			if(reimbursementDTO.getId()!=0) {
				responseEntity=new ResponseEntity<ReimbursementDTO>(reimbursementDTO,HttpStatus.OK);
			}else {
				responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
		}
	//EndPoint 5
	@PutMapping("reimbursements/<reimbursementid>/process")
	public ResponseEntity<?> processReimbursements(@Valid@RequestBody ProcessReimbursementDTO processReimbursementDTO){
		String result=reimbursementServiceImpl.processReimbursementRequests(processReimbursementDTO);
		if(result.equals("success")) {
			return new ResponseEntity<>("Updated",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	
	
}
