package com.app.vendorinvoice.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PurchaseOrderItemResponse {
	private String productName;
	private Integer quantity;
	private BigDecimal unitPrice;
	private BigDecimal lineAmount;
}
