package com.app.vendorinvoice.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.vendorinvoice.constants.PurchaseOrderStatus;
import com.app.vendorinvoice.dto.request.PurchaseOrderRequest;
import com.app.vendorinvoice.dto.response.PurchaseOrderItemResponse;
import com.app.vendorinvoice.dto.response.PurchaseOrderResponse;
import com.app.vendorinvoice.entity.PurchaseOrder;
import com.app.vendorinvoice.entity.PurchaseOrderItem;
import com.app.vendorinvoice.entity.Vendor;
import com.app.vendorinvoice.exception.BadRequestException;
import com.app.vendorinvoice.exception.ResourceNotFoundException;
import com.app.vendorinvoice.repository.PurchaseOrderRepository;
import com.app.vendorinvoice.repository.VendorRepository;
import com.app.vendorinvoice.service.CurrentLoggedInUserService;
import com.app.vendorinvoice.service.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchaseOrderServiceImpl implements  PurchaseOrderService {

	private final PurchaseOrderRepository poRepository;
	private final CurrentLoggedInUserService currentLoggedInUserService;
    private final VendorRepository vendorRepository;
	
	public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepository, CurrentLoggedInUserService currentLoggedInUserService,
			VendorRepository vendorRepository) {
		this.poRepository = poRepository;
		this.currentLoggedInUserService = currentLoggedInUserService;
		this.vendorRepository=vendorRepository;
	}

	@Override
	public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {
		log.info("Creating Purchase Order {}", request.getPoNumber());
		Vendor vendor = vendorRepository.findById(request.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
		
		PurchaseOrder po = new PurchaseOrder();
		po.setPoNumber(request.getPoNumber());
        po.setVendor(vendor);
        List<PurchaseOrderItem> items = request.getItems().stream().map(i -> {
        	PurchaseOrderItem item = new PurchaseOrderItem();
        	item.setProductName(i.getProductName());
            item.setQuantity(i.getQuantity());
            item.setUnitPrice(i.getUnitPrice());
            item.setPurchaseOrder(po);
            return item;
        }).toList();
        
        po.setItems(items);
		BigDecimal total = items.stream().map(PurchaseOrderItem::getUnitPrice).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		po.setTotalPoAmount(total);
		po.setCreatedBy(currentLoggedInUserService.getCurrentUserId());
		return mapToResponse(poRepository.save(po));
	}

	@Override
	public PurchaseOrderResponse submitPurchaseOrder(Long poId) {
		PurchaseOrder po = getPurchaseOrder(poId);
		if(po.getStatus() != PurchaseOrderStatus.DRAFT) {
			throw new BadRequestException("Only DRAFT PO can be submitted");
		}
		po.setStatus(PurchaseOrderStatus.SUBMITTED);
		return mapToResponse(poRepository.save(po));
	}

	@Override
	public PurchaseOrderResponse approvePurchaseOrder(Long poId) {
		PurchaseOrder po = getPurchaseOrder(poId);
		if(po.getStatus() != PurchaseOrderStatus.SUBMITTED) {
			throw new BadRequestException("Only SUBMITTED PO can be approved");
		}
		po.setStatus(PurchaseOrderStatus.APPROVED);
		return mapToResponse(po);
	}
	
	private PurchaseOrder getPurchaseOrder(Long poId) {
		return poRepository.findById(poId).orElseThrow(() -> new ResourceNotFoundException("Purchase Order Not Found"));
	}
	
	private PurchaseOrderResponse mapToResponse(PurchaseOrder po) {
		PurchaseOrderResponse response = new PurchaseOrderResponse();
		response.setId(po.getPoId());
        response.setPoNumber(po.getPoNumber());
        response.setStatus(po.getStatus().name());
        response.setTotalAmount(po.getTotalPoAmount());
        List<PurchaseOrderItemResponse> list = po.getItems().stream().map(item -> {
        	PurchaseOrderItemResponse r = new PurchaseOrderItemResponse();
        	 r.setProductName(item.getProductName());
             r.setQuantity(item.getQuantity());
             r.setUnitPrice(item.getUnitPrice());
             r.setLineAmount(item.getLineAmount());
             return r;
        }).toList();
        response.setItems(list);
        return response;
	}
}
