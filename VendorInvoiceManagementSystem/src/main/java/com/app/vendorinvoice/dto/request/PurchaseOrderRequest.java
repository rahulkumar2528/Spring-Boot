package com.app.vendorinvoice.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class PurchaseOrderRequest {

	private String poNumber;
    private Long vendorId;
    private List<PurchaseOrderItemRequest> items;
}
