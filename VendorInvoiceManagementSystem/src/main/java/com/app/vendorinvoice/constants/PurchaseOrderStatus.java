package com.app.vendorinvoice.constants;

public enum PurchaseOrderStatus {

	DRAFT((short) 1, "Draft"),
	SUBMITTED((short) 2, "Submitted"),
	APPROVED((short) 3, "Approved"),
	REJECTED((short) 4, "Rejected"), 
	CLOSED((short) 5, "Closed");

	private final short code;
	private final String description;
	
	PurchaseOrderStatus(short code, String description) {
        this.code = code;
        this.description = description;
    }
	
	public short getCode() {
        return code;
    }
	
	 public static PurchaseOrderStatus fromCode(short code) {
	        for (PurchaseOrderStatus s : values()) {
	            if (s.code == code) return s;
	        }
	        throw new IllegalArgumentException("Invalid PO Status Code: " + code);
	    }
}
