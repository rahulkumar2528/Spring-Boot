package com.app.vendorinvoice.service;

import com.app.vendorinvoice.dto.request.PurchaseOrderRequest;
import com.app.vendorinvoice.dto.response.PurchaseOrderResponse;

public interface PurchaseOrderService {

	PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request);

    PurchaseOrderResponse submitPurchaseOrder(Long poId);

    PurchaseOrderResponse approvePurchaseOrder(Long poId);
}
