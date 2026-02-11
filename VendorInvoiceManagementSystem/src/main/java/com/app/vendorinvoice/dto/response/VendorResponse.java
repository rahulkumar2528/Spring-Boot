package com.app.vendorinvoice.dto.response;

import lombok.Data;

@Data
public class VendorResponse {

	private Long vendorId;
    private String vendorCode;
    private String vendorName;
    private String gstNumber;
    private String email;
    private int status;
}
