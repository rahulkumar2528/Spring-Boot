package com.app.vendorinvoice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.vendorinvoice.dto.request.VendorRequest;
import com.app.vendorinvoice.dto.response.VendorResponse;
import com.app.vendorinvoice.entity.Vendor;
import com.app.vendorinvoice.exception.BadRequestException;
import com.app.vendorinvoice.exception.ResourceNotFoundException;
import com.app.vendorinvoice.repository.VendorRepository;
import com.app.vendorinvoice.service.CurrentLoggedInUserService;
import com.app.vendorinvoice.service.VendorService;

import jakarta.transaction.Transactional;

@Service
public class VendorServiceImpl implements VendorService {

	private final VendorRepository vendorRepository;
	
	private final CurrentLoggedInUserService currentLoggedInUserService;
	
	public VendorServiceImpl(VendorRepository vendorRepository, CurrentLoggedInUserService currentLoggedInUserService) {
		this.vendorRepository = vendorRepository;
		this.currentLoggedInUserService = currentLoggedInUserService;
	}
	
	@Override
	@Transactional
	public VendorResponse createVendor(VendorRequest request) {
		if(vendorRepository.existsByVendorCode(request.getVendorCode())) {
			throw new BadRequestException("Vendor code already exists");
		}
		String currentUserId = currentLoggedInUserService.getCurrentUserId();
		Vendor vendor = new Vendor();
		vendor.setVendorName(request.getVendorName());
		vendor.setVendorCode(request.getVendorCode());
		vendor.setVendorEmail(request.getVendorEmail());
		vendor.setBankAccountNo(request.getBankAccountNo());
		vendor.setGstNumber(request.getGstNumber());
		vendor.setIfscCode(request.getIfscCode());
		vendor.setCreatedBy(currentUserId);
		return mapToResponse(vendorRepository.save(vendor));
	}

	private VendorResponse mapToResponse(Vendor vendor) {
		VendorResponse vendorResponse = new  VendorResponse();
		vendorResponse.setVendorId(vendor.getVendorId());
		vendorResponse.setVendorCode(vendor.getVendorCode());
		vendorResponse.setVendorName(vendor.getVendorName());
		vendorResponse.setEmail(vendor.getVendorEmail());
		vendorResponse.setGstNumber(vendor.getGstNumber());
		vendorResponse.setStatus(vendor.getStatus());
		return vendorResponse;
	}

	@Override
	public VendorResponse getVendor(Long vendorId) {
		Vendor vendor = vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id " + vendorId));
		return mapToResponse(vendor);
	}

	@Override
	public List<VendorResponse> getAllVendors() {
		List<Vendor> vendorList = vendorRepository.findAll();
		if(vendorList.isEmpty()) {
			throw new ResourceNotFoundException("No Data Available");
		}
		return vendorList.stream().map(this :: mapToResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public VendorResponse updateVendor(Long vendorId, VendorRequest request) {
		if (vendorId == null) {
			throw new BadRequestException("Required parameter missing");
		}

		Vendor vendor = vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id " + vendorId));
		
		vendor.setVendorName(request.getVendorName());
		vendor.setVendorEmail(request.getVendorEmail());
		vendor.setBankAccountNo(request.getBankAccountNo());
		vendor.setGstNumber(request.getGstNumber());
		vendor.setIfscCode(request.getIfscCode());
		return mapToResponse(vendor);
	}

	@Override
	public void deleteVendor(Long vendorId) {
		Vendor vendor = vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id " + vendorId));
		vendorRepository.delete(vendor);
	}
}
