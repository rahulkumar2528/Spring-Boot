package com.app.vendorinvoice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.app.vendorinvoice.constants.Constants;
import com.app.vendorinvoice.dto.request.PurchaseOrderRequest;
import com.app.vendorinvoice.dto.response.PurchaseOrderResponse;
import com.app.vendorinvoice.service.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(Constants.CONT_PURCHASEORDER)
public class PurchaseOrderController {

	private final PurchaseOrderService poService;
	
	public PurchaseOrderController(PurchaseOrderService poService) {
		this.poService=poService;
	}
	
	@PostMapping(Constants.CREATE_PURCHASE_ORDER)
	public ResponseEntity<PurchaseOrderResponse> createPurchaseOrder(@RequestBody PurchaseOrderRequest poRequest){
		 log.info("API: Create Purchase Order");
		 return new ResponseEntity<>(poService.createPurchaseOrder(poRequest), HttpStatus.CREATED);
	}
	
	@PatchMapping(Constants.SUBMIT_PURCHASE_ORDER+"/{poId}")
	public ResponseEntity<PurchaseOrderResponse> submitPurchaseOrder(@PathVariable Long poId) {
		log.info("API: Submit Purchase Order");
		return ResponseEntity.ok(poService.submitPurchaseOrder(poId));
	}

	@PatchMapping(Constants.APPROVE_PURCHASE_ORDER+"/{poId}")
	public ResponseEntity<PurchaseOrderResponse> approvePurchaseOrder(@PathVariable Long poId) {
		log.info("API: Approve Purchase Order");
		return ResponseEntity.ok(poService.submitPurchaseOrder(poId));
	}
}
