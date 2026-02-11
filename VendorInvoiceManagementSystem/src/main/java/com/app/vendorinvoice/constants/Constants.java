package com.app.vendorinvoice.constants;

public interface Constants {

	/***************************Controller EndPoints ***************************/
	public static final String CONT_AUTH = "api/auth";
	public static final String CONT_VENDEOR = "api/vendors";
	public static final String CONT_INVOICE = "api/invoice";
	public static final String CONT_PURCHASEORDER = "api/purchase-orders";
	public static final String CONT_INVOICEUPLOAD = "api/invoice-upload";
	public static final String CONT_APPROVAL = "api/approvals";
	public static final String CONT_PAYMENT = "api/payments";
	public static final String CONT_DOCUMENT_UPLOAD = "api/dms";
	
	/***************************Master Status***************************/
	public static final short STATUS_ACTIVE = 1;
	public static final short STATUS_INACTIVE = 0;
	
	public static final String CREATE_VENDOR="/createVendor";
	public static final String GET_VENDOR="/getVendor";
	public static final String GET_ALL_VENDOR="/getAllVendor";
	public static final String UPDATE_VENDOR="/updateVendor";
	public static final String DELETE_VENDOR="/deleteVendor";
	
	public static final String CREATE_PURCHASE_ORDER="/createPuchaseOrder";
	public static final String SUBMIT_PURCHASE_ORDER="/submitPuchaseOrder";
	public static final String APPROVE_PURCHASE_ORDER="/approvePuchaseOrder";
}
