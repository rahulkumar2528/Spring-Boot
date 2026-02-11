package com.app.vendorinvoice.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PurchaseOrderResponse {
	private Long id;
	private String poNumber;
	private String status;
	private BigDecimal totalAmount;
	private List<PurchaseOrderItemResponse> items;
}
