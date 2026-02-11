package com.app.vendorinvoice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.vendorinvoice.constants.Constants;
import com.app.vendorinvoice.dto.request.VendorRequest;
import com.app.vendorinvoice.dto.response.VendorResponse;
import com.app.vendorinvoice.service.VendorService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.CONT_VENDEOR)
public class VendorController {
	
	private final VendorService vendorService;
	
	public VendorController(VendorService vendorService) {
		this.vendorService= vendorService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(Constants.CREATE_VENDOR)
	public ResponseEntity<VendorResponse> createVendor(@RequestBody VendorRequest request){
		return new ResponseEntity<>(vendorService.createVendor(request), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(Constants.GET_VENDOR+"/{vendorId}")
	public VendorResponse getVendor(@PathVariable Long vendorId){
		return vendorService.getVendor(vendorId);
	}
	
	@PreAuthorize("hasAnyRole('FINANCE', 'ADMIN')")
	@GetMapping(Constants.GET_ALL_VENDOR)
	public ResponseEntity<List<VendorResponse>> getAllVendor(){
		List<VendorResponse> vendorList = vendorService.getAllVendors();
		return new ResponseEntity<List<VendorResponse>>(vendorList, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(Constants.UPDATE_VENDOR+"/{vendorId}")
	public VendorResponse updateVendor(@PathVariable Long vendorId, @RequestBody VendorRequest request) {
		return vendorService.updateVendor(vendorId, request);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(Constants.DELETE_VENDOR+"/{vendorId}")
	public ResponseEntity<String> deleteVendor(@PathVariable Long vendorId) {
		vendorService.deleteVendor(vendorId);
		return 	ResponseEntity.ok("Vendor deleted successfully");
	}
}
