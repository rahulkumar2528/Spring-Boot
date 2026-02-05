package com.app.vendorinvoice.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "vendor", uniqueConstraints = {
		@UniqueConstraint(name = "vendor_vendor_code_key", columnNames = "vendor_code") })
@Data
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private Long vendorId;

	@Column(name = "vendor_code", length = 20, nullable = false)
	private String vendorCode;

	@Column(name = "vendor_name", length = 100, nullable = false)
	private String vendorName;

	@Column(name = "vendor_email", length = 100)
	private String vendorEmail;

	@Column(name = "gst_number", length = 15)
	private String gstNumber;

	@Column(name = "bank_account_no", length = 30)
	private String bankAccountNo;

	@Column(name = "ifsc_code", length = 15)
	private String ifscCode;

	@Column(name = "status")
	private Short status;

	@Column(name = "created_by", length = 15)
	private String createdBy;

	@Column(name = "created_date")
	private OffsetDateTime createdDate;
}
