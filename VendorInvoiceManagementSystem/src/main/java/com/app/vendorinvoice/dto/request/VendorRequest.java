package com.app.vendorinvoice.dto.request;

import lombok.Data;

@Data
public class VendorRequest {
	private String vendorCode;
	private String vendorName;
	private String vendorEmail;
	private String gstNumber;
	private String bankAccountNo;
	private String ifscCode;
}
