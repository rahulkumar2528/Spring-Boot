package com.app.vendorinvoice.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PurchaseOrderItemRequest {
	private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
}
