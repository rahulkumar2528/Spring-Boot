package com.app.vendorinvoice.service;

import java.util.List;

import com.app.vendorinvoice.dto.request.VendorRequest;
import com.app.vendorinvoice.dto.response.VendorResponse;

public interface VendorService {

	public VendorResponse createVendor(VendorRequest request);
	
	public VendorResponse getVendor(Long id);
	
	List<VendorResponse> getAllVendors();
	
	VendorResponse updateVendor(Long vendorId, VendorRequest request);
	
	void deleteVendor(Long vendorId);
}
